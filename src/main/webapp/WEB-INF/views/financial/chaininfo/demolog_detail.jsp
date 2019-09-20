<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'上链日志详情'" >
    <div data-options="region:'center',border:false" style="overflow: auto;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <input type="hidden" id="id" name="id" value="${demoLogVO.id}"/>
            <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                <tr>
                    <td>接口：${demoLogVO.requestUrl }</td>
                </tr>

                <tr>
                    <td>请求数据：${demoLogVO.requestData }</td>
                </tr>

                <tr>
                    <td>返回结果：${demoLogVO.responseData }</td>
                </tr>

                <tr>
                    <td>接口信息：${demoLogVO.errorMessage }</td>
                </tr>
            </table>
        </div>
    </div>
</div>
