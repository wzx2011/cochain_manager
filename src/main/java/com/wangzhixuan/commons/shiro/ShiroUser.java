/**
 *
 */
package com.wangzhixuan.commons.shiro;

import java.io.Serializable;
import java.util.Set;

/**
 * @description：自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    
    private Long id;
    private final String loginName;
    private String name;
    private String password;
    private Integer enterpriseId;
    /** 以太坊用户*/
    private String ethereumAccount;
    /** 以太坊密码*/
    private String ethereumPassword;
    private Set<String> urlSet;
    private Set<String> roles;
    private Set<String> roleTypes;

    public ShiroUser(String loginName) {
        this.loginName = loginName;
    }

    public ShiroUser(Long id, String loginName, String name, Set<String> urlSet) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.urlSet = urlSet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Integer enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getUrlSet() {
        return urlSet;
    }

    public void setUrlSet(Set<String> urlSet) {
        this.urlSet = urlSet;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public Set<String> getRoleTypes() {
        return roleTypes;
    }

    public void setRoleTypes(Set<String> roleTypes) {
        this.roleTypes = roleTypes;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getEthereumAccount() {
        return ethereumAccount;
    }

    public void setEthereumAccount(String ethereumAccount) {
        this.ethereumAccount = ethereumAccount;
    }

    public String getEthereumPassword() {
        return ethereumPassword;
    }

    public void setEthereumPassword(String ethereumPassword) {
        this.ethereumPassword = ethereumPassword;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
//    @Override
//    public String toString() {
//        return loginName;
//    }
    @Override
    public String toString() {
        return name;
    }
}