package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 用户信息 实体类
 * @author wzx
 * @create 2019年05月07日 14:03:15
**/
public class UserApiDO {

    private Integer id;
    private String name;
    private String loginName;
    private String password;
    private String mobile;
    private String telephone;
    private String mail;
    private String token;
    private Date refreshTime;
    private Integer expiresIn;
    private String keyStorePath;
    private Integer state;
    private Date createTime;
    private String userAddr;
    private Date modifyTime;
    private String appid;
    private Byte role;
    private String secretkey;
    private String txHash;

    public UserApiDO() {
        super();
    }

    public UserApiDO(Integer id,String name,String loginName,String password,String mobile,String telephone,String mail,String token,Date refreshTime,Integer expiresIn,String keyStorePath,Integer state,Date createTime,String userAddr,Date modifyTime,String appid,Byte role,String secretkey,String txHash) {
        super();
        this.id = id;
        this.name = name;
        this.loginName = loginName;
        this.password = password;
        this.mobile = mobile;
        this.telephone = telephone;
        this.mail = mail;
        this.token = token;
        this.refreshTime = refreshTime;
        this.expiresIn = expiresIn;
        this.keyStorePath = keyStorePath;
        this.state = state;
        this.createTime = createTime;
        this.userAddr = userAddr;
        this.modifyTime = modifyTime;
        this.appid = appid;
        this.role = role;
        this.secretkey = secretkey;
        this.txHash = txHash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public Date getRefreshTime() {
        return refreshTime;
    }

    public void setRefreshTime(Date refreshTime) {
        this.refreshTime = refreshTime;
    }
    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }
    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }
    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }
    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String toString(Integer id,String name,String loginName,String password,String mobile,String telephone,String mail,String token,Date refreshTime,Integer expiresIn,String keyStorePath,Integer state,Date createTime,String userAddr,Date modifyTime,String appid,Byte role,String secretkey,String txHash) {
        return "UserApi:【this.id:"+id+",this.name:"+name+",this.loginName:"+loginName+",this.password:"+password+",this.mobile:"+mobile+",this.telephone:"+telephone+",this.mail:"+mail+",this.token:"+token+",this.refreshTime:"+refreshTime+",this.expiresIn:"+expiresIn+",this.keyStorePath:"+keyStorePath+",this.state:"+state+",this.createTime:"+createTime+",this.userAddr:"+userAddr+",this.modifyTime:"+modifyTime+",this.appid:"+appid+",this.role:"+role+",this.secretkey:"+secretkey+",this.txHash:"+txHash+"】";
    }


}