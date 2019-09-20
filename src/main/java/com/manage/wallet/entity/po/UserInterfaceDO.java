package com.manage.wallet.entity.po;

/**
 * @program: cochain_manager
 * @description: 钱包用户接口参数实体类
 * @author: wzx
 * @create: 2019-06-26 09:18
 */
public class UserInterfaceDO {

    private String userUid;
    private String userPassword;

    public String getUserUid() {
        return userUid;
    }

    public void setUserUid(String userUid) {
        this.userUid = userUid;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
