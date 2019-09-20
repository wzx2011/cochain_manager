package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 应用日志信息 实体类
 * @author wzx
 * @create 2019年05月07日 14:20:29
**/
public class AppLogDO {
	
    private Integer id;
    private String appid;
    private String userid;
    private String uri;
    private String requestData;
    private String requestHeader;
    private Date createTime;
    
    public AppLogDO() {
        super();
    }
    
    public AppLogDO(Integer id,String appid,String userid,String uri,String requestData,String requestHeader,Date createTime) {
        super();
        this.id = id;
        this.appid = appid;
        this.userid = userid;
        this.uri = uri;
        this.requestData = requestData;
        this.requestHeader = requestHeader;
        this.createTime = createTime;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
    public String getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(String requestHeader) {
        this.requestHeader = requestHeader;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
 	
 	public String toString(Integer id,String appid,String userid,String uri,String requestData,String requestHeader,Date createTime) {
        return "AppLog:【this.id:"+id+",this.appid:"+appid+",this.userid:"+userid+",this.uri:"+uri+",this.requestData:"+requestData+",this.requestHeader:"+requestHeader+",this.createTime:"+createTime+"】";
    }
    

}