package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 常量信息 实体类
 * @author wzx
 * @create 2019年05月07日 11:42:11
**/
public class ConstantsDO {
	
    private Integer id;
    private String key;
    private String value;
    private String comments;
    private Integer deleteFlag;
    
    public ConstantsDO() {
        super();
    }
    
    public ConstantsDO(Integer id,String key,String value,String comments,Integer deleteFlag) {
        super();
        this.id = id;
        this.key = key;
        this.value = value;
        this.comments = comments;
        this.deleteFlag = deleteFlag;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }
 	
 	public String toString(Integer id,String key,String value,String comments,Integer deleteFlag) {
        return "Constants:【this.id:"+id+",this.key:"+key+",this.value:"+value+",this.comments:"+comments+",this.deleteFlag:"+deleteFlag+"】";
    }
    

}