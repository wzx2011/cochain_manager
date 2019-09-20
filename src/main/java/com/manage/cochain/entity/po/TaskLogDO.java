package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 任务日志信息 实体类
 * @author wzx
 * @create 2019年05月07日 11:45:23
**/
public class TaskLogDO {

    private Integer id;
    private String artifactId;
    private String txHash;
    private Integer state;
    private Date modifyTime;
    private Date createTime;
    private String message;
    private String className;
    private String dataSource;

    public TaskLogDO() {
        super();
    }

    public TaskLogDO(Integer id,String artifactId,String txHash,Integer state,Date modifyTime,Date createTime,String message,String className,String dataSource) {
        super();
        this.id = id;
        this.artifactId = artifactId;
        this.txHash = txHash;
        this.state = state;
        this.modifyTime = modifyTime;
        this.createTime = createTime;
        this.message = message;
        this.className = className;
        this.dataSource = dataSource;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String toString(Integer id,String artifactId,String txHash,Integer state,Date modifyTime,Date createTime,String message,String className,String dataSource) {
        return "TaskLog:【this.id:"+id+",this.artifactId:"+artifactId+",this.txHash:"+txHash+",this.state:"+state+",this.modifyTime:"+modifyTime+",this.createTime:"+createTime+",this.message:"+message+",this.className:"+className+",this.dataSource:"+dataSource+"】";
    }


}