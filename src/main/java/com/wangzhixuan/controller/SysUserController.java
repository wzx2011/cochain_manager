package com.wangzhixuan.controller;

import java.util.*;

import javax.validation.Valid;

import com.manage.util.StringUtil;
import com.wangzhixuan.model.SysRole;
import com.wangzhixuan.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.commons.shiro.PasswordHash;
import com.wangzhixuan.commons.shiro.ShiroDbRealm;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.service.ISysUserService;

/**
 * @description：用户管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/user")
public class SysUserController extends BaseController {
    @Autowired
    private ISysUserService userService;
    @Autowired
    private PasswordHash passwordHash;

    /**
     * 用户管理页
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/user/user";
    }

    /**
     * 从首页跳转到用户管理页面
     *
     * @return
     */
    @GetMapping("/manager1")
    public String manager1(Model model) {
        // 添加默认状态
        model.addAttribute("userStatus","2");
        return "admin/user/user";
    }

    /**
     * 用户管理列表
     *
     * @param userVo
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Map<String, Object> condition = new HashMap<String, Object>();
        Set<String> roles = getShiroUser().getRoles();
        Integer enterpriseId = getShiroUser().getEnterpriseId();
        if (StringUtils.isNotBlank(userVo.getName())) {
            condition.put("name", userVo.getName());
        }
        if (userVo.getOrganizationId() != null) {
            // 部门id不为且大于0 则根据部门id查询 否则根据企业id查询
            if(userVo.getOrganizationId()>0){
                condition.put("organizationId", userVo.getOrganizationId());
            }else {
                condition.put("enterpriseId",-userVo.getOrganizationId());
            }

        }
        if (StringUtil.isNotBlank(userVo.getCreatedateStartStr())) {
            condition.put("startTime", userVo.getCreatedateStartStr());
        }
        if (StringUtil.isNotBlank(userVo.getCreatedateEndStr())) {
            condition.put("endTime", userVo.getCreatedateEndStr());
        }
        if (userVo.getStatus() != null){
            condition.put("status",userVo.getStatus());
        }

        if (roles.contains("admin")) {
            condition.put("roleType",true);
        }else{
            // 如果没有超级管理员权限
            condition.put("enterpriseId",enterpriseId);
        }
        pageInfo.setCondition(condition);
        userService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 添加用户页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage() {
        return "admin/user/userAdd";
    }

    /**
     * 添加用户
     *
     * @param userVo
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid UserVo userVo) {
        List<SysUser> list = userService.selectByLoginName(userVo);
        if (list != null && !list.isEmpty()) {
            return renderError("登录名已存在!");
        }
        String salt = StringUtils.getUUId();
        String pwd = passwordHash.toHex(userVo.getPassword(), salt);
        userVo.setSalt(salt);
        userVo.setPassword(pwd);
        Set<String> roles = getShiroUser().getRoles();
        // 如果没有超级管理员权限
        if (!roles.contains("admin") && StringUtil.isNotBlank(getShiroUser().getEnterpriseId())) {
            userVo.setEnterpriseId(getShiroUser().getEnterpriseId());
        }
        userService.insertByVo(userVo);
        return renderSuccess("添加成功");
    }


    /**
     * 编辑用户页
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editPage")
    public String editPage(Model model, Long id) {
        UserVo userVo = userService.selectVoById(id);
        List<SysRole> rolesList = userVo.getRolesList();
        List<Long> ids = new ArrayList<Long>();
        for (SysRole role : rolesList) {
            ids.add(role.getId());
        }
        model.addAttribute("roleIds", ids);
        model.addAttribute("user", userVo);
        return "admin/user/userEdit";
    }

    /**
     * 编辑用户
     *
     * @param userVo
     * @return
     */
//    @RequiresRoles("admin")
    @PostMapping("/edit")
    @ResponseBody
    public Object edit(@Valid UserVo userVo) {
        List<SysUser> list = userService.selectByLoginName(userVo);
        if (list != null && !list.isEmpty()) {
            return renderError("登录名已存在!");
        }
        // 更新密码
        if (StringUtils.isNotBlank(userVo.getPassword())) {
            SysUser user = userService.selectById(userVo.getId());
            String salt = user.getSalt();
            String pwd = passwordHash.toHex(userVo.getPassword(), salt);
            userVo.setPassword(pwd);
        }
        userService.updateByVo(userVo);
        return renderSuccess("修改成功！");
    }

    /**
     * 修改密码页
     *
     * @return
     */
    @GetMapping("/editPwdPage")
    public String editPwdPage() {
        return "admin/user/userEditPwd";
    }

    @Autowired
    private ShiroDbRealm shiroDbRealm;
    
    /**
     * 修改密码
     *
     * @param oldPwd
     * @param pwd
     * @return
     */
    @PostMapping("/editUserPwd")
    @ResponseBody
    public Object editUserPwd(String oldPwd, String pwd) {
        SysUser user = userService.selectById(getUserId());
        String salt = user.getSalt();
        if (!user.getPassword().equals(passwordHash.toHex(oldPwd, salt))) {
            return renderError("老密码不正确!");
        }
        // 修改密码时清理用户的缓存
        shiroDbRealm.removeUserCache(user.getLoginName());
        userService.updatePwdByUserId(getUserId(), passwordHash.toHex(pwd, salt));
        return renderSuccess("密码修改成功！");
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
//    @RequiresRoles("admin")
    @PostMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        Long currentUserId = getUserId();
        if (id == currentUserId) {
            return renderError("不可以删除自己！");
        }
        userService.deleteUserById(id);
        return renderSuccess("删除成功！");
    }
}