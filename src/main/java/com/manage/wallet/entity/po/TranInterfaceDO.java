package com.manage.wallet.entity.po;

/**
 * @program: cochain_manager
 * @description: 转移token接口参数实体类
 * @author: wzx
 * @create: 2019-06-26 09:19
 */
public class TranInterfaceDO {

    private String tranTokenId;
    private String tranAmount;
    private String tranFromId;
    private String tranToId;
    private String tranPassword;

    public String getTranTokenId() {
        return tranTokenId;
    }

    public void setTranTokenId(String tranTokenId) {
        this.tranTokenId = tranTokenId;
    }

    public String getTranAmount() {
        return tranAmount;
    }

    public void setTranAmount(String tranAmount) {
        this.tranAmount = tranAmount;
    }

    public String getTranFromId() {
        return tranFromId;
    }

    public void setTranFromId(String tranFromId) {
        this.tranFromId = tranFromId;
    }

    public String getTranToId() {
        return tranToId;
    }

    public void setTranToId(String tranToId) {
        this.tranToId = tranToId;
    }

    public String getTranPassword() {
        return tranPassword;
    }

    public void setTranPassword(String tranPassword) {
        this.tranPassword = tranPassword;
    }
}
