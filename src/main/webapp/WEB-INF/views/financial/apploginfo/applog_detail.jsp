<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'上链日志详情'" >
    <div data-options="region:'center',border:false" style="overflow: auto;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <input type="hidden" name="id" value="${appLogVO.id}"/>
            <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                <tr>
                    <td>请求数据：${appLogVO.requestData }</td>
                </tr>

                <tr>
                    <td>请求头数据：${appLogVO.requestHeader }</td>
                </tr>
            </table>
        </div>
    </div>
</div>
