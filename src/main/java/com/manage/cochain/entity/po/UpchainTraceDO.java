package com.manage.cochain.entity.po;

/**
 * @program: apitable
 * @description: 交易信息上链实体
 * @author: wzx
 * @create: 2019-05-21 10:15
 */
public class UpchainTraceDO {

    private String uidT;
    private String secretkeyT;
    private String appidT;
    private String appkeyT;
    private String ipPortT;
    private String projectValueT;
    private String projectUrlT;
    private String parameterT;

    public UpchainTraceDO(){}

    public UpchainTraceDO(String uidT, String secretkeyT, String appidT, String appkeyT, String ipPortT, String projectValueT, String projectUrlT, String parameterT) {
        this.uidT = uidT;
        this.secretkeyT = secretkeyT;
        this.appidT = appidT;
        this.appkeyT = appkeyT;
        this.ipPortT = ipPortT;
        this.projectValueT = projectValueT;
        this.projectUrlT = projectUrlT;
        this.parameterT = parameterT;
    }

    public String getUidT() {
        return uidT;
    }

    public void setUidT(String uidT) {
        this.uidT = uidT;
    }

    public String getSecretkeyT() {
        return secretkeyT;
    }

    public void setSecretkeyT(String secretkeyT) {
        this.secretkeyT = secretkeyT;
    }

    public String getAppidT() {
        return appidT;
    }

    public void setAppidT(String appidT) {
        this.appidT = appidT;
    }

    public String getAppkeyT() {
        return appkeyT;
    }

    public void setAppkeyT(String appkeyT) {
        this.appkeyT = appkeyT;
    }

    public String getIpPortT() {
        return ipPortT;
    }

    public void setIpPortT(String ipPortT) {
        this.ipPortT = ipPortT;
    }

    public String getProjectValueT() {
        return projectValueT;
    }

    public void setProjectValueT(String projectValueT) {
        this.projectValueT = projectValueT;
    }

    public String getProjectUrlT() {
        return projectUrlT;
    }

    public void setProjectUrlT(String projectUrlT) {
        this.projectUrlT = projectUrlT;
    }

    public String getParameterT() {
        return parameterT;
    }

    public void setParameterT(String parameterT) {
        this.parameterT = parameterT;
    }

    @Override
    public String toString() {
        return "UpchainTraceDO{" +
                "uidT='" + uidT + '\'' +
                ", secretkeyT='" + secretkeyT + '\'' +
                ", appidT='" + appidT + '\'' +
                ", appkeyT='" + appkeyT + '\'' +
                ", ipPortT='" + ipPortT + '\'' +
                ", projectValueT='" + projectValueT + '\'' +
                ", projectUrlT='" + projectUrlT + '\'' +
                ", parameterT='" + parameterT + '\'' +
                '}';
    }
}
