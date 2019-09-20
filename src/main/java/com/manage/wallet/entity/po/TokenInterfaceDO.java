package com.manage.wallet.entity.po;

import java.util.Date;

/**
 * @program: cochain_manager
 * @description: 发放token接口参数实体类
 * @author: wzx
 * @create: 2019-06-26 09:19
 */
public class TokenInterfaceDO {

    private String tokenAccountId;
    private String tokenPassword;
    private String tokenAmount;
    private String tokenEndTime;

    public String getTokenAccountId() {
        return tokenAccountId;
    }

    public void setTokenAccountId(String tokenAccountId) {
        this.tokenAccountId = tokenAccountId;
    }

    public String getTokenPassword() {
        return tokenPassword;
    }

    public void setTokenPassword(String tokenPassword) {
        this.tokenPassword = tokenPassword;
    }

    public String getTokenAmount() {
        return tokenAmount;
    }

    public void setTokenAmount(String tokenAmount) {
        this.tokenAmount = tokenAmount;
    }

    public String getTokenEndTime() {
        return tokenEndTime;
    }

    public void setTokenEndTime(String tokenEndTime) {
        this.tokenEndTime = tokenEndTime;
    }
}
