package com.manage.wallet.entity.po;

import java.util.Date;

/**
 * 钱包积分信息 实体类
 * @author wzx
 * @create 2019年06月12日 13:50:56
**/
public class WalletTokenDO {
	
    private Integer id;
    private String name;
    private Integer owner;
    private Integer amount;
    private String hash;
    private Date createTime;
    private Date endTime;
    private Integer state;
    private String tranhash;
    
    public WalletTokenDO() {
        super();
    }
    
    public WalletTokenDO(Integer id,String name,Integer owner,Integer amount,String hash,Date createTime,Date endTime,Integer state,String tranhash) {
        super();
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.amount = amount;
        this.hash = hash;
        this.createTime = createTime;
        this.endTime = endTime;
        this.state = state;
        this.tranhash = tranhash;
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
    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    public String getTranhash() {
        return tranhash;
    }

    public void setTranhash(String tranhash) {
        this.tranhash = tranhash;
    }
 	
 	public String toString(Integer id,String name,Integer owner,Integer amount,String hash,Date createTime,Date endTime,Integer state,String tranhash) {
        return "WalletToken:【this.id:"+id+",this.name:"+name+",this.owner:"+owner+",this.amount:"+amount+",this.hash:"+hash+",this.createTime:"+createTime+",this.endTime:"+endTime+",this.state:"+state+",this.tranhash:"+tranhash+"】";
    }
    

}