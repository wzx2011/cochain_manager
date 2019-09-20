package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 上链日志信息 实体类
 * @author wzx
 * @create 2019年05月22日 17:17:05
**/
public class DemoLogDO {
	
    private Integer id;
    private String requestUrl;
    private String requestData;
    private String responseData;
    private Integer status;
    private String errorMessage;
    private Date createTime;
    
    public DemoLogDO() {
        super();
    }
    
    public DemoLogDO(Integer id,String requestUrl,String requestData,String responseData,Integer status,String errorMessage,Date createTime) {
        super();
        this.id = id;
        this.requestUrl = requestUrl;
        this.requestData = requestData;
        this.responseData = responseData;
        this.status = status;
        this.errorMessage = errorMessage;
        this.createTime = createTime;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }
    public String getRequestData() {
        return requestData;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }
    public String getResponseData() {
        return responseData;
    }

    public void setResponseData(String responseData) {
        this.responseData = responseData;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
 	
 	public String toString(Integer id,String requestUrl,String requestData,String responseData,Integer status,String errorMessage,Date createTime) {
        return "DemoLog:【this.id:"+id+",this.requestUrl:"+requestUrl+",this.requestData:"+requestData+",this.responseData:"+responseData+",this.status:"+status+",this.errorMessage:"+errorMessage+",this.createTime:"+createTime+"】";
    }
    

}