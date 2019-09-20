<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchAppLogForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'应用日志列表'">
        <table id="appLogDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<div id="appLogToolbar" style="display: none;">
    <shiro:hasPermission name="/goUpdAppLogPage">
        <a onclick="appLogDetailFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-search icon-purple'">详情</a>
    </shiro:hasPermission>
</div>

<script>
    var appLogDataGrid ;
    $(function () {
        // 加载日志列表数据
        getAppLogListData();

    });

    // 初始化日志列表数据
    function getAppLogListData(){
        appLogDataGrid = $('#appLogDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getAppLogList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            toolbar: "#appLogToolbar",
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'appid',
                title: '应用编号',
                align: 'center',
                width: 50
            }, {
                field: 'userid',
                title: '用户编号',
                align: 'center',
                width: 50
            }, {
                field: 'uri',
                title: '接口',
                align: 'center',
                width: 50
            }, {
                field: 'requestData',
                title: '请求数据',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'requestHeader',
                title: '请求头数据',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                sortable: true,
                width: 50
            }]],
            onLoadError: function () {

            }
        });
    }

    // 详情
    function appLogDetailFun(){
        var row = appLogDataGrid.datagrid("getSelections");
        if (null == row || row.length == 0) {
            $.messager.alert('提示', '请选择一条信息！', 'info');
            return;
        }

        parent.$.modalDialog({
            title : '上链日志详情',
            width : 800,
            height : 480,
            href : projectPath + 'goUpdAppLogPage?id='+row[0].id
        });
    }
</script>
