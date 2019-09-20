package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 应用信息 实体类
 * @author wzx
 * @create 2019年05月07日 14:19:49
**/
public class AppDO {
	
    private Integer id;
    private String name;
    private String contractId;
    private String appid;
    private String appkey;
    private String token;
    private Integer expire;
    private String url;
    private Date createTime;
    private Date modifyTime;
    
    public AppDO() {
        super();
    }
    
    public AppDO(Integer id,String name,String contractId,String appid,String appkey,String token,Integer expire,String url,Date createTime,Date modifyTime) {
        super();
        this.id = id;
        this.name = name;
        this.contractId = contractId;
        this.appid = appid;
        this.appkey = appkey;
        this.token = token;
        this.expire = expire;
        this.url = url;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
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
    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String toString(Integer id,String name,String contractId,String appid,String appkey,String token,Integer expire,String url,Date createTime) {
        return "App:【this.id:"+id+",this.name:"+name+",this.contractId:"+contractId+",this.appid:"+appid+",this.appkey:"+appkey+",this.token:"+token+",this.expire:"+expire+",this.url:"+url+",this.createTime:"+createTime+"】";
    }
    

}