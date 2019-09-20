package com.manage.util;

import java.util.List;

/**
 * @program: coscf
 * @description: java后台响应数据
 * @author: wzx
 * @create: 2019-04-15 14:39
 */
public class ResponseData {

    // 响应信息
    private String msg;
    // 响应代码
    private String code;
    // 响应map集合数据
    private Object objData;
    // 响应list集合数据
    private List<?> listData;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getObjData() {
        return objData;
    }

    public void setObjData(Object objData) {
        this.objData = objData;
    }

    public List<?> getListData() {
        return listData;
    }

    public void setListData(List<?> listData) {
        this.listData = listData;
    }
}
