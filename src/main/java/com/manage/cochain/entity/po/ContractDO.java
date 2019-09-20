package com.manage.cochain.entity.po;

import java.util.Date;

/**
 * 合约信息 实体类
 * @author wzx
 * @create 2019年05月07日 11:43:20
**/
public class ContractDO {

    private Integer id;
    private String name;
    private String code;
    private String contractAddress;
    private String contractGas;
    private String walletFilePath;
    private String web3Url;
    private String walletPwd;
    private Integer attemptCount;
    private Integer sleepDuration;
    private Integer state;
    private Date createTime;
    private String defaultUser;
    private String keyStorePath;
    private Integer chainId;

    public ContractDO() {
        super();
    }

    public ContractDO(Integer id,String name,String code,String contractAddress,String contractGas,String walletFilePath,String web3Url,String walletPwd,Integer attemptCount,Integer sleepDuration,Integer state,Date createTime,String defaultUser,String keyStorePath,Integer chainId) {
        super();
        this.id = id;
        this.name = name;
        this.code = code;
        this.contractAddress = contractAddress;
        this.contractGas = contractGas;
        this.walletFilePath = walletFilePath;
        this.web3Url = web3Url;
        this.walletPwd = walletPwd;
        this.attemptCount = attemptCount;
        this.sleepDuration = sleepDuration;
        this.state = state;
        this.createTime = createTime;
        this.defaultUser = defaultUser;
        this.keyStorePath = keyStorePath;
        this.chainId = chainId;
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
    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }
    public String getContractGas() {
        return contractGas;
    }

    public void setContractGas(String contractGas) {
        this.contractGas = contractGas;
    }
    public String getWalletFilePath() {
        return walletFilePath;
    }

    public void setWalletFilePath(String walletFilePath) {
        this.walletFilePath = walletFilePath;
    }
    public String getWeb3Url() {
        return web3Url;
    }

    public void setWeb3Url(String web3Url) {
        this.web3Url = web3Url;
    }
    public String getWalletPwd() {
        return walletPwd;
    }

    public void setWalletPwd(String walletPwd) {
        this.walletPwd = walletPwd;
    }
    public Integer getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(Integer attemptCount) {
        this.attemptCount = attemptCount;
    }
    public Integer getSleepDuration() {
        return sleepDuration;
    }

    public void setSleepDuration(Integer sleepDuration) {
        this.sleepDuration = sleepDuration;
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
    public String getDefaultUser() {
        return defaultUser;
    }

    public void setDefaultUser(String defaultUser) {
        this.defaultUser = defaultUser;
    }
    public String getKeyStorePath() {
        return keyStorePath;
    }

    public void setKeyStorePath(String keyStorePath) {
        this.keyStorePath = keyStorePath;
    }
    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public String toString(Integer id,String name,String code,String contractAddress,String contractGas,String walletFilePath,String web3Url,String walletPwd,Integer attemptCount,Integer sleepDuration,Integer state,Date createTime,String defaultUser,String keyStorePath,Integer chainId) {
        return "Contract:【this.id:"+id+",this.name:"+name+",this.code:"+code+",this.contractAddress:"+contractAddress+",this.contractGas:"+contractGas+",this.walletFilePath:"+walletFilePath+",this.web3Url:"+web3Url+",this.walletPwd:"+walletPwd+",this.attemptCount:"+attemptCount+",this.sleepDuration:"+sleepDuration+",this.state:"+state+",this.createTime:"+createTime+",this.defaultUser:"+defaultUser+",this.keyStorePath:"+keyStorePath+",this.chainId:"+chainId+"】";
    }


}