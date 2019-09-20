package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 任务信息 实体类
 * @author wzx
 * @create 2019年05月07日 11:44:45
**/
public class TaskDO {

    private Integer id;
    private String name;
    private String code;
    private String corn;
    private String respPersonPhone;
    private String respPersonName;
    private String respPersonMail;
    private Integer state;
    private Date createTime;
    private Integer sleep;
    private Integer retry;
    private Integer isOpen;
    private Integer isTaskExcute;

    public TaskDO() {
        super();
    }

    public TaskDO(Integer id,String name,String code,String corn,String respPersonPhone,String respPersonName,String respPersonMail,Integer state,Date createTime,Integer sleep,Integer retry,Integer isOpen,Integer isTaskExcute) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.corn = corn;
        this.respPersonPhone = respPersonPhone;
        this.respPersonName = respPersonName;
        this.respPersonMail = respPersonMail;
        this.state = state;
        this.createTime = createTime;
        this.sleep = sleep;
        this.retry = retry;
        this.isOpen = isOpen;
        this.isTaskExcute = isTaskExcute;
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
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    public String getCorn() {
        return corn;
    }

    public void setCorn(String corn) {
        this.corn = corn;
    }
    public String getRespPersonPhone() {
        return respPersonPhone;
    }

    public void setRespPersonPhone(String respPersonPhone) {
        this.respPersonPhone = respPersonPhone;
    }
    public String getRespPersonName() {
        return respPersonName;
    }

    public void setRespPersonName(String respPersonName) {
        this.respPersonName = respPersonName;
    }
    public String getRespPersonMail() {
        return respPersonMail;
    }

    public void setRespPersonMail(String respPersonMail) {
        this.respPersonMail = respPersonMail;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getSleep() {
        return sleep;
    }

    public void setSleep(Integer sleep) {
        this.sleep = sleep;
    }
    public Integer getRetry() {
        return retry;
    }

    public void setRetry(Integer retry) {
        this.retry = retry;
    }
    public Integer getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Integer isOpen) {
        this.isOpen = isOpen;
    }
    public Integer getIsTaskExcute() {
        return isTaskExcute;
    }

    public void setIsTaskExcute(Integer isTaskExcute) {
        this.isTaskExcute = isTaskExcute;
    }

    public String toString(Integer id,String name,String code,String corn,String respPersonPhone,String respPersonName,String respPersonMail,Integer state,Date createTime,Integer sleep,Integer retry,Integer isOpen,Integer isTaskExcute) {
        return "Task:【this.id:"+id+",this.name:"+name+",this.code:"+code+",this.corn:"+corn+",this.respPersonPhone:"+respPersonPhone+",this.respPersonName:"+respPersonName+",this.respPersonMail:"+respPersonMail+",this.state:"+state+",this.createTime:"+createTime+",this.sleep:"+sleep+",this.retry:"+retry+",this.isOpen:"+isOpen+",this.isTaskExcute:"+isTaskExcute+"】";
    }


}