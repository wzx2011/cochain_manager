package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 远程接口上链路径信息 实体类
 * @author wzx
 * @create 2019年05月17日 09:34:37
**/
public class DemoUrlDO {
	
    private Integer id;
    private String description;
    private String projectValue;
    private String projectUrl;
    private Integer urlType;
    private String remark;
    private Date createTime;
    
    public DemoUrlDO() {
        super();
    }

    public DemoUrlDO(Integer id, String description, String projectValue, String projectUrl, Integer urlType, String remark, Date createTime) {
        super();
        this.id = id;
        this.description = description;
        this.projectValue = projectValue;
        this.projectUrl = projectUrl;
        this.urlType = urlType;
        this.remark = remark;
        this.createTime = createTime;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(String projectValue) {
        this.projectValue = projectValue;
    }
    public String getProjectUrl() {
        return projectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        this.projectUrl = projectUrl;
    }
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUrlType() {
        return urlType;
    }

    public void setUrlType(Integer urlType) {
        this.urlType = urlType;
    }

    @Override
    public String toString() {
        return "DemoUrlDO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", projectValue='" + projectValue + '\'' +
                ", projectUrl='" + projectUrl + '\'' +
                ", urlType=" + urlType +
                ", remark='" + remark + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}