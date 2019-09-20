package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 日志信息 实体类
 * @author wzx
 * @create 2019年05月07日 11:44:02
**/
public class LogDO {

    private Integer id;
    private String requestUser;
    private String requestApi;
    private String requestData;
    private Date createTime;

    public LogDO() {
        super();
    }

    public LogDO(Integer id,String requestUser,String requestApi,String requestData,Date createTime) {
        super();
        this.id = id;
        this.requestUser = requestUser;
        this.requestApi = requestApi;
        this.requestData = requestData;
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(String requestUser) {
        this.requestUser = requestUser;
    }
    public String getRequestApi() {
        return requestApi;
    }

    public void setRequestApi(String requestApi) {
        this.requestApi = requestApi;
    }
    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString(Integer id,String requestUser,String requestApi,String requestData,Date createTime) {
        return "Log:【this.id:"+id+",this.requestUser:"+requestUser+",this.requestApi:"+requestApi+",this.requestData:"+requestData+",this.createTime:"+createTime+"】";
    }


}