package com.manage.util;

import java.util.ArrayList;
import java.util.List;

/**
 * COWITP
 *Json返回工具类
 * @author zhangfeng
 * @create 2018-09-30 13:17
 **/
public class JsonResult {
    //成功1  错误0 部分成功2 空用户-1
    public static final Integer JSON_RESULT_SUCCESS = Integer.valueOf(1);

    public static final Integer JSON_RESULT_FAILED = Integer.valueOf(0);

    public static final Integer JSON_RESULT_SUCCESS_PART = Integer.valueOf(2);

    public static final Integer JSON_RESULT_NULL_USER = Integer.valueOf(-1);

    private final List<Object> data = new ArrayList();
    private Integer returnCode;
    private String msg;
    private String html;

    protected JsonResult() {
    }

    private JsonResult(Integer returnCode, String msg) {
        this.returnCode = returnCode;
        this.msg = msg;
    }
    //两个成功两个部分成功一个失败的方法
    public static JsonResult createSuccess() {
        return new JsonResult(JSON_RESULT_SUCCESS, null);
    }

    public static JsonResult createSuccess(String msg) {
        return new JsonResult(JSON_RESULT_SUCCESS, msg);
    }

    public static JsonResult createSuccessPart() {
        JsonResult jsonResult = new JsonResult(JSON_RESULT_SUCCESS_PART, null);
        return jsonResult;
    }

    public static JsonResult createSuccessPart(String msg) {
        JsonResult jsonResult = new JsonResult(JSON_RESULT_SUCCESS_PART, msg);
        return jsonResult;
    }

    public static JsonResult createFalied(String msg) {
        JsonResult jsonResult = new JsonResult(JSON_RESULT_FAILED, msg);
        return jsonResult;
    }

    public static JsonResult createNullUserResult() {
        JsonResult jsonResult = new JsonResult(JSON_RESULT_NULL_USER, "用户信息为空");
        jsonResult.addData("");
        return jsonResult;
    }

    public Integer getReturnCode() {
        return this.returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Object> getData() {
        return this.data;
    }

    public void addData(Object obj) {
        this.data.add(obj);
    }

    public String getHtml() {
        return this.html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public void removeDataAll() {
        if (this.data != null) {
            this.data.clear();
        }
    }

    public void addDataAll(List list) {
        if (null != list) {
            this.data.addAll(list);
        }
    }
}