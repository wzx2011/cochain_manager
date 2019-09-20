package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 远程网址端口信息 实体类
 * @author wzx
 * @create 2019年05月17日 09:31:55
**/
public class DemoIpDO {
	
    private Integer id;
    private String description;
    private String ipPort;
    private String remark;
    private Date createTime;
    
    public DemoIpDO() {
        super();
    }
    
    public DemoIpDO(Integer id,String description,String ipPort,String remark,Date createTime) {
        super();
        this.id = id;
        this.description = description;
        this.ipPort = ipPort;
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
    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
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
 	
 	public String toString(Integer id,String description,String ipPort,String remark,Date createTime) {
        return "DemoIp:【this.id:"+id+",this.description:"+description+",this.ipPort:"+ipPort+",this.remark:"+remark+",this.createTime:"+createTime+"】";
    }
    

}