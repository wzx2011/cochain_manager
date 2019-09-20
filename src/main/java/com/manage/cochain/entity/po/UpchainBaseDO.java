package com.manage.cochain.entity.po;

/**
 * @program: apitable
 * @description: 基础信息上链实体
 * @author: wzx
 * @create: 2019-05-18 07:38
 */
public class UpchainBaseDO {

    private String uidB;
    private String secretkeyB;
    private String appidB;
    private String appkeyB;
    private String ipPortB;
    private String projectValueB;
    private String projectUrlB;
    private String parameterB;

    public UpchainBaseDO(){}

    public UpchainBaseDO(String uidB, String secretkeyB, String appidB, String appkeyB, String ipPortB, String projectValueB, String projectUrlB, String parameterB) {
        this.uidB = uidB;
        this.secretkeyB = secretkeyB;
        this.appidB = appidB;
        this.appkeyB = appkeyB;
        this.ipPortB = ipPortB;
        this.projectValueB = projectValueB;
        this.projectUrlB = projectUrlB;
        this.parameterB = parameterB;
    }

    public String getUidB() {
        return uidB;
    }

    public void setUidB(String uidB) {
        this.uidB = uidB;
    }

    public String getSecretkeyB() {
        return secretkeyB;
    }

    public void setSecretkeyB(String secretkeyB) {
        this.secretkeyB = secretkeyB;
    }

    public String getAppidB() {
        return appidB;
    }

    public void setAppidB(String appidB) {
        this.appidB = appidB;
    }

    public String getAppkeyB() {
        return appkeyB;
    }

    public void setAppkeyB(String appkeyB) {
        this.appkeyB = appkeyB;
    }

    public String getIpPortB() {
        return ipPortB;
    }

    public void setIpPortB(String ipPortB) {
        this.ipPortB = ipPortB;
    }

    public String getProjectValueB() {
        return projectValueB;
    }

    public void setProjectValueB(String projectValueB) {
        this.projectValueB = projectValueB;
    }

    public String getProjectUrlB() {
        return projectUrlB;
    }

    public void setProjectUrlB(String projectUrlB) {
        this.projectUrlB = projectUrlB;
    }

    public String getParameterB() {
        return parameterB;
    }

    public void setParameterB(String parameterB) {
        this.parameterB = parameterB;
    }

    @Override
    public String toString() {
        return "UpchainBaseDO{" +
                "uidB='" + uidB + '\'' +
                ", secretkeyB='" + secretkeyB + '\'' +
                ", appidB='" + appidB + '\'' +
                ", appkeyB='" + appkeyB + '\'' +
                ", ipPortB='" + ipPortB + '\'' +
                ", projectValueB='" + projectValueB + '\'' +
                ", projectUrlB='" + projectUrlB + '\'' +
                ", parameterB='" + parameterB + '\'' +
                '}';
    }
}
