package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 商品编码信息 实体类
 * @author wzx
 * @create 2019年05月07日 11:40:41
**/
public class ArtifactIdsDO {

    private Integer id;
    private String type;
    private String hash;
    private Integer state;
    private Date modifyTime;
    private Date createTime;
    private String appid;
    private String userid;

    public ArtifactIdsDO() {
        super();
    }

    public ArtifactIdsDO(Integer id,String type,String hash,Integer state,Date modifyTime,Date createTime,String appid,String userid) {
        super();
        this.id = id;
        this.type = type;
        this.hash = hash;
        this.state = state;
        this.modifyTime = modifyTime;
        this.createTime = createTime;
        this.appid = appid;
        this.userid = userid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
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

    public String toString(Integer id,String type,String hash,Integer state,Date modifyTime,Date createTime,String appid,String userid) {
        return "ArtifactIds:【this.id:"+id+",this.type:"+type+",this.hash:"+hash+",this.state:"+state+",this.modifyTime:"+modifyTime+",this.createTime:"+createTime+",this.appid:"+appid+",this.userid:"+userid+"】";
    }


}