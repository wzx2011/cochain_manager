package com.manage.wallet.entity.po;

import java.util.Date;

/**
 * 钱包用户信息 实体类
 * @author wzx
 * @create 2019年06月12日 13:50:10
**/
public class WalletUserDO {
	
    private Integer id;
    private String name;
    private String loginName;
    private String password;
    private String mobile;
    private String address;
    private String ks;
    private Integer split;
    private Integer state;
    private Integer fails;
    private Date modifyTime;
    private Date createTime;
    private Date lockTime;
    private String ipaddr;
    
    public WalletUserDO() {
        super();
    }
    
    public WalletUserDO(Integer id,String name,String loginName,String password,String mobile,String address,String ks,Integer split,Integer state,Integer fails,Date modifyTime,Date createTime,Date lockTime,String ipaddr) {
        super();
        this.id = id;
        this.name = name;
        this.loginName = loginName;
        this.password = password;
        this.mobile = mobile;
        this.address = address;
        this.ks = ks;
        this.split = split;
        this.state = state;
        this.fails = fails;
        this.modifyTime = modifyTime;
        this.createTime = createTime;
        this.lockTime = lockTime;
        this.ipaddr = ipaddr;
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
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }
    public Integer getSplit() {
        return split;
    }

    public void setSplit(Integer split) {
        this.split = split;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Integer getFails() {
        return fails;
    }

    public void setFails(Integer fails) {
        this.fails = fails;
    }
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getLockTime() {
        return lockTime;
    }

    public void setLockTime(Date lockTime) {
        this.lockTime = lockTime;
    }
    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }
 	
 	public String toString(Integer id,String name,String loginName,String password,String mobile,String address,String ks,Integer split,Integer state,Integer fails,Date modifyTime,Date createTime,Date lockTime,String ipaddr) {
        return "WalletUser:【this.id:"+id+",this.name:"+name+",this.loginName:"+loginName+",this.password:"+password+",this.mobile:"+mobile+",this.address:"+address+",this.ks:"+ks+",this.split:"+split+",this.state:"+state+",this.fails:"+fails+",this.modifyTime:"+modifyTime+",this.createTime:"+createTime+",this.lockTime:"+lockTime+",this.ipaddr:"+ipaddr+"】";
    }
    

}