package com.manage.wallet.entity.po;

import java.util.Date;

/**
 * 钱包交易信息 实体类
 * @author wzx
 * @create 2019年06月12日 13:49:34
**/
public class WalletTransactionDO {
	
    private Integer id;
    private String txHash;
    private Integer fromid;
    private Integer toid;
    private Integer tokenid;
    private Integer amount;
    private Date createTime;
    private Date recordTime;
    private Integer state;
    
    public WalletTransactionDO() {
        super();
    }
    
    public WalletTransactionDO(Integer id,String txHash,Integer fromid,Integer toid,Integer tokenid,Integer amount,Date createTime,Date recordTime,Integer state) {
        super();
        this.id = id;
        this.txHash = txHash;
        this.fromid = fromid;
        this.toid = toid;
        this.tokenid = tokenid;
        this.amount = amount;
        this.createTime = createTime;
        this.recordTime = recordTime;
        this.state = state;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }
    public Integer getFromid() {
        return fromid;
    }

    public void setFromid(Integer fromid) {
        this.fromid = fromid;
    }
    public Integer getToid() {
        return toid;
    }

    public void setToid(Integer toid) {
        this.toid = toid;
    }
    public Integer getTokenid() {
        return tokenid;
    }

    public void setTokenid(Integer tokenid) {
        this.tokenid = tokenid;
    }
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getRecordTime() {
        return recordTime;
    }

    public void setRecordTime(Date recordTime) {
        this.recordTime = recordTime;
    }
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
 	
 	public String toString(Integer id,String txHash,Integer fromid,Integer toid,Integer tokenid,Integer amount,Date createTime,Date recordTime,Integer state) {
        return "WalletTransaction:【this.id:"+id+",this.txHash:"+txHash+",this.fromid:"+fromid+",this.toid:"+toid+",this.tokenid:"+tokenid+",this.amount:"+amount+",this.createTime:"+createTime+",this.recordTime:"+recordTime+",this.state:"+state+"】";
    }
    

}