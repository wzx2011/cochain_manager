package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 交易数据池 实体类
 * @author wzx
 * @create 2019年08月22日 10:22:10
**/
public class TransactionPoolDO {
	
    private Integer id;
    private String baseHash;
    private String hash;
    private String value;
    private String type;
    private String user;
    private Integer state;
    private String artifactId;
    private String txHash;
    private Date modifyTime;
    private Date createTime;
    private String appIndex;
    
    public TransactionPoolDO() {
        super();
    }
    
    public TransactionPoolDO(Integer id,String baseHash,String hash,String value,String type,String user,Integer state,String artifactId,String txHash,Date modifyTime,Date createTime,String appIndex) {
        super();
        this.id = id;
        this.baseHash = baseHash;
        this.hash = hash;
        this.value = value;
        this.type = type;
        this.user = user;
        this.state = state;
        this.artifactId = artifactId;
        this.txHash = txHash;
        this.modifyTime = modifyTime;
        this.createTime = createTime;
        this.appIndex = appIndex;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getBaseHash() {
        return baseHash;
    }

    public void setBaseHash(String baseHash) {
        this.baseHash = baseHash;
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
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
    public String getAppIndex() {
        return appIndex;
    }

    public void setAppIndex(String appIndex) {
        this.appIndex = appIndex;
    }
 	
 	public String toString(Integer id,String baseHash,String hash,String value,String type,String user,Integer state,String artifactId,String txHash,Date modifyTime,Date createTime,String appIndex) {
        return "TransactionPool:【this.id:"+id+",this.baseHash:"+baseHash+",this.hash:"+hash+",this.value:"+value+",this.type:"+type+",this.user:"+user+",this.state:"+state+",this.artifactId:"+artifactId+",this.txHash:"+txHash+",this.modifyTime:"+modifyTime+",this.createTime:"+createTime+",this.appIndex:"+appIndex+"】";
    }
    

}