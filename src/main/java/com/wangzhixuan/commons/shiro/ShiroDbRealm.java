package com.wangzhixuan.commons.shiro;

import com.manage.util.StringUtil;
import com.manage.util.TimeUtil;
import com.wangzhixuan.model.SysUser;
import com.wangzhixuan.model.vo.UserVo;
import com.wangzhixuan.service.IRoleService;
import com.wangzhixuan.service.ISysUserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description：shiro权限认证
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class ShiroDbRealm extends AuthorizingRealm {
    private static final Logger LOGGER = LogManager.getLogger(ShiroDbRealm.class);

    @Autowired private ISysUserService userService;
    @Autowired private IRoleService roleService;
    
    public ShiroDbRealm(CacheManager cacheManager, CredentialsMatcher matcher) {
        super(cacheManager, matcher);
    }
    
    /**
     * Shiro登录认证(原理：用户提交 用户名和密码  --- shiro 封装令牌 ---- realm 通过用户名将密码查询返回 ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        LOGGER.info("Shiro开始登录认证");
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        UserVo uservo = new UserVo();
        uservo.setLoginName(token.getUsername());
        List<SysUser> list = userService.selectByLoginName(uservo);
        // 账号不存在
        if (list == null || list.isEmpty()) {
            return null;
        }
        SysUser user = list.get(0);
        // 账号未启用
        if (user.getStatus() == 1) {
            LOGGER.info("用户："+user.getLoginName()+"未启用！！！"+TimeUtil.getCurrentStrDate());
            throw new DisabledAccountException("账号未启用！");
        }
        // 账号未启用
        if (user.getStatus() == 2) {
            LOGGER.info("用户："+user.getLoginName()+"未审核！！！"+TimeUtil.getCurrentStrDate());
            throw new DisabledAccountException("账号尚未审核，请等待审核通过！");
        }
        // 校验用户的过期时间
        Date deadline = user.getDeadline();
        if(StringUtil.isNotBlank(deadline)){
            long daysBetween = TimeUtil.getDaysBetween(TimeUtil.getCurrentDate(),deadline);
            if (daysBetween<0){
                LOGGER.info("用户："+user.getLoginName()+"已过期！！！"+TimeUtil.getCurrentStrDate());
                throw new DisabledAccountException("账号已过期！");
            }
        }
        // 读取用户的url和角色
        Map<String, Set<String>> resourceMap = roleService.selectResourceMapByUserId(user.getId());
        Set<String> urls = resourceMap.get("urls");
        Set<String> roles = resourceMap.get("roles");
        Set<String> roleTypes = resourceMap.get("roleTypes");
        ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginName(), user.getName(), urls);
        shiroUser.setRoles(roles);
        shiroUser.setRoleTypes(roleTypes);
        shiroUser.setPassword(user.getPassword());
        shiroUser.setEnterpriseId(user.getEnterpriseId());
        shiroUser.setEthereumAccount(user.getEthereumAccount());
        shiroUser.setEthereumPassword(user.getEthereumPassword());
        // 认证缓存信息
        return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), 
                ShiroByteSource.of(user.getSalt()), getName());
    }

    /**
     * Shiro权限认证
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setRoles(shiroUser.getRoles());
        info.addStringPermissions(shiroUser.getUrlSet());
        
        return info;
    }

    @Override
    protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) super.getAvailablePrincipal(principals);
        return shiroUser.toString();
    }

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) super.getAvailablePrincipal(principals);
        return shiroUser.toString();
    }

    /**
     * 清除用户缓存
     * @param shiroUser
     */
    public void removeUserCache(ShiroUser shiroUser){
        SimplePrincipalCollection principals = new SimplePrincipalCollection();
        principals.add(shiroUser, super.getName());
        super.clearCache(principals);
    }

    /**
     * 清除用户缓存
     * @param loginName
     */
    public void removeUserCache(String loginName){
        removeUserCache(new ShiroUser(loginName));
    }
}
