package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 远程接口项目信息 实体类
 * @author wzx
 * @create 2019年05月17日 09:33:36
**/
public class DemoProjectDO {
	
    private Integer id;
    private String projectName;
    private String projectValue;
    private String remark;
    private Date createTime;
    
    public DemoProjectDO() {
        super();
    }
    
    public DemoProjectDO(Integer id,String projectName,String projectValue,String remark,Date createTime) {
        super();
        this.id = id;
        this.projectName = projectName;
        this.projectValue = projectValue;
        this.remark = remark;
        this.createTime = createTime;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    public String getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(String projectValue) {
        this.projectValue = projectValue;
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
 	
 	public String toString(Integer id,String projectName,String projectValue,String remark,Date createTime) {
        return "DemoProject:【this.id:"+id+",this.projectName:"+projectName+",this.projectValue:"+projectValue+",this.remark:"+remark+",this.createTime:"+createTime+"】";
    }
    

}