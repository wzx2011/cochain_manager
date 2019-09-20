package com.wangzhixuan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wangzhixuan.commons.shiro.PasswordHash;
import com.wangzhixuan.commons.shiro.ShiroDbRealm;
import com.wangzhixuan.model.SysRole;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.service.impl.RoleServiceImpl;
import com.wangzhixuan.service.impl.SysUserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.shiro.captcha.DreamCaptcha;
import com.wangzhixuan.commons.utils.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description：登录退出
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
public class LoginController extends BaseController {
    @Autowired
    private DreamCaptcha dreamCaptcha;

    @Autowired
    private SysUserServiceImpl userService;

    @Autowired
    private PasswordHash passwordHash;

    @Autowired
    private ShiroDbRealm shiroDbRealm;

    @Autowired
    private RoleServiceImpl roleService;
    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    /**
     * 首页
     *
     * @param model
     * @return
     */
    @GetMapping("/index")
    public String index(Model model) {
        return "index";
    }

    /**
     * GET 登录
     * @return {String}
     */
    @GetMapping("/login")
    public String login() {
        logger.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    @GetMapping("/getRoleTypeId")
    @ResponseBody
    public Map<String,Object> getRoleTypeId(){
        List<SysRole> roleTypeIds = roleService.getRoleTypeId();
        Map<String,Object> map = new HashMap<>();
        map.put("roleTypeIds",roleTypeIds);
        return  map;
    }

    /**
     * 添加用户
     *
     * @return
     */
    @PostMapping("/doRegister")
    @ResponseBody
    public Object doRegister(HttpServletRequest request, HttpServletResponse response,
                             String loginName,String name ,String password,
                             String phone,String email,Integer sex,Integer userType,String roleIds) {
        UserVo userVo = new UserVo();
        userVo.setLoginName(loginName);
        userVo.setPassword(password);
        userVo.setPhone(phone);
        userVo.setEmail(email);
        userVo.setName(name);
        userVo.setSex(sex);
        userVo.setUserType(userType);
        userVo.setRoleIds(roleIds);
        // 校验用户名
        List<SysUser> list = userService.selectByLoginName(userVo);
        if (list != null && !list.isEmpty()) {
            return renderError("登录名已存在!");
        }
        String salt = StringUtils.getUUId();
        String pwd = passwordHash.toHex(userVo.getPassword(), salt);
        userVo.setSalt(salt);
        userVo.setPassword(pwd);
        userService.insertUser(userVo);
        return renderSuccess("注册成功！");
    }
    /**
     * POST 登录 shiro 写法
     *
     * @param username 用户名
     * @param password 密码
     * @return {Object}
     */
    @PostMapping("/login")
    @ResponseBody
    public Object loginPost(HttpServletRequest request, HttpServletResponse response,
            String username, String password, String captcha, 
            @RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe) {
        logger.info("POST请求登录");
        // 登录时清空用户shiro缓存
        shiroDbRealm.removeUserCache(username);
        // 改为全部抛出异常，避免ajax csrf token被刷新
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("用户名不能为空");
        }
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("密码不能为空");
        }
//        if (StringUtils.isBlank(captcha)) {
//            throw new RuntimeException("验证码不能为空");
//        }
//        if (!dreamCaptcha.validate(request, response, captcha)) {
//            throw new RuntimeException("验证码错误");
//        }
        Subject user = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        // 设置记住密码
        token.setRememberMe(1 == rememberMe);
        try {
            user.login(token);
            return renderSuccess();
        } catch (UnknownAccountException e) {
            throw new RuntimeException("账号不存在！", e);
        } catch (DisabledAccountException e) {
            throw new RuntimeException(e.getMessage(), e);
        } catch (IncorrectCredentialsException e) {
            throw new RuntimeException("密码错误！", e);
        } catch (Throwable e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 未授权
     * @return {String}
     */
    @GetMapping("/unauth")
    public String unauth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "unauth";
    }

    /**
     * 退出
     * @return {Result}
     */
    @PostMapping("/logout")
    @ResponseBody
    public Object logout() {
        logger.info("登出");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return renderSuccess();
    }

}
