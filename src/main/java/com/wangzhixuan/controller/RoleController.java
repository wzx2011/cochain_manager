package com.wangzhixuan.controller;

import java.util.*;

import javax.validation.Valid;

import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.result.PageInfo;
import com.wangzhixuan.service.IRoleService;

/**
 * @description：权限管理
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    private IRoleService roleService;

    /**
     * 权限管理页
     *
     * @return
     */
    @GetMapping("/manager")
    public String manager() {
        return "admin/role/role";
    }

    /**
     * 权限列表
     *
     * @param page
     * @param rows
     * @param sort
     * @param order
     * @return
     */
    @PostMapping("/dataGrid")
    @ResponseBody
    public Object dataGrid(Integer page, Integer rows, String sort, String order) {
        PageInfo pageInfo = new PageInfo(page, rows, sort, order);
        Set<String> roles = getShiroUser().getRoles();
        Map<String,Object> map = new HashMap<>();
        if(roles.contains("admin")){
            map.put("roleTypes",true);
        }else{
            // 如果没有超级管理员权限
            Integer enterpriseId = getShiroUser().getEnterpriseId();
            map.put("enterpriseId",enterpriseId);
        }
        pageInfo.setCondition(map);
        roleService.selectDataGrid(pageInfo);
        return pageInfo;
    }

    /**
     * 权限树
     * 用户编辑页面的角色信息
     * @return
     */
    @PostMapping("/tree")
    @ResponseBody
    public Object tree() {
        ShiroUser shiroUser = getShiroUser();
        Set<String> roles = shiroUser.getRoles();
        SysRole role = new SysRole();
        if(roles.contains("gys")){
            role.setName("gys");
        }
        if(roles.contains("hxqy")){
            role.setName("hxqy");
        }
        if(roles.contains("zjf")){
            role.setName("hxqy");
        }
        if(roles.contains("admin")){
            role.setName("admin");
        }else{
            role.setEnterpriseId(shiroUser.getEnterpriseId());
        }

        return roleService.selectTree(role);
    }

    /**
     * 权限树
     * 管理员用户添加编辑角色
     * @return
     */
    @PostMapping("/tree1")
    @ResponseBody
    public Object tree1() {
        ShiroUser shiroUser = getShiroUser();
        Set<String> roles = shiroUser.getRoles();
        SysRole role = new SysRole();
        if(roles.contains("gys")){
            role.setName("gys");
        }
        if(roles.contains("hxqy")){
            role.setName("hxqy");
        }
        if(roles.contains("zjf")){
            role.setName("hxqy");
        }
        if(roles.contains("admin")){
            role.setName("admin");
        }else{
            role.setEnterpriseId(shiroUser.getEnterpriseId());
        }

        return roleService.selectTree1(role);
    }

    /**
     * 添加权限页
     *
     * @return
     */
    @GetMapping("/addPage")
    public String addPage(Model model) {
        Set<String> roleTypes = getShiroUser().getRoleTypes();
        model.addAttribute("roleTypes",roleTypes);
        return "admin/role/roleAdd";
    }

    /**
     * 添加权限
     *
     * @param role
     * @return
     */
    @PostMapping("/add")
    @ResponseBody
    public Object add(@Valid SysRole role) {
        Set<String> roles = getShiroUser().getRoles();
        // 如果没有超级管理员权限
        if (!roles.contains("admin")) {
            Integer enterpriseId = getShiroUser().getEnterpriseId();
            role.setEnterpriseId(enterpriseId);
        }
        roleService.insert(role);
        return renderSuccess("添加成功！");
    }

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(Long id) {
        roleService.deleteById(id);
        return renderSuccess("删除成功！");
    }

    /**
     * 编辑权限页
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/editPage")
    public String editPage(Model model, Long id) {
        SysRole role = roleService.selectById(id);
        Set<String> roleTypes = getShiroUser().getRoleTypes();
        model.addAttribute("roleTypes",roleTypes);
        model.addAttribute("role", role);
        return "admin/role/roleEdit";
    }

    /**
     * 删除权限
     *
     * @param role
     * @return
     */
    @RequestMapping("/edit")
    @ResponseBody
    public Object edit(@Valid SysRole role) {
        roleService.updateById(role);
        return renderSuccess("编辑成功！");
    }

    /**
     * 授权页面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/grantPage")
    public String grantPage(Model model, Long id) {
        model.addAttribute("id", id);
        return "admin/role/roleGrant";
    }

    /**
     * 授权页面页面根据角色查询资源
     *
     * @param id
     * @return
     */
    @RequestMapping("/findResourceIdListByRoleId")
    @ResponseBody
    public Object findResourceByRoleId(Long id) {
        List<Long> resources = roleService.selectResourceIdListByRoleId(id);
        return renderSuccess(resources);
    }

    /**
     * 授权
     *
     * @param id
     * @param resourceIds
     * @return
     */
//    @RequiresRoles("admin")
    @RequestMapping("/grant")
    @ResponseBody
    public Object grant(Long id, String resourceIds) {
        roleService.updateRoleResource(id, resourceIds);
        return renderSuccess("授权成功！");
    }

}
