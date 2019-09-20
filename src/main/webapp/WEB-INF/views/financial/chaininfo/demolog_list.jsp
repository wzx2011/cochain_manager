<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchDemoLogForm">
            <table>
                <tr>
                    <th>接口：</th>
                    <td><input class="easyui-textbox" name="requestUrl"/>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchDemoLogFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanDemoLogFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'上链日志列表'">
        <table id="demoLogDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<div id="demoLogToolbar" style="display: none;">
    <shiro:hasPermission name="/goUpdDemoLogPage">
        <a onclick="viewDemoLogFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-search icon-purple'">详情</a>
    </shiro:hasPermission>
</div>

<script>
    var demoLogDataGrid ;
    $(function () {
        // 加载上链日志列表数据
        getDemoLogListData();

    });

    // 初始化上链日志列表数据
    function getDemoLogListData(){
        demoLogDataGrid = $('#demoLogDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getDemoLogList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            toolbar: "#demoLogToolbar",
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'requestUrl',
                title: '接口',
                align: 'center',
                width: 50
            }, {
                field: 'requestData',
                title: '请求数据',
                align: 'center',
                width: 50
            }, {
                field: 'responseData',
                title: '返回结果',
                align: 'center',
                width: 50
            }, {
                field: 'status',
                title: '调用状态',
                align: 'center',
                sortable: true,
                width: 20,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:green">调用出错</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">成功返回</span>';
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'errorMessage',
                title: '接口信息',
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

    // 查询
    function searchDemoLogFun() {
        demoLogDataGrid.datagrid('load', $.serializeObject($('#searchDemoLogForm')));
    }
    // 清空
    function cleanDemoLogFun() {
        $('#searchDemoLogForm input').val('');
        $("#searchDemoLogForm").form('clear');
        demoLogDataGrid.datagrid('load', {});
    }

    // 查看上链日志详情
    function viewDemoLogFun(){
        var row = demoLogDataGrid.datagrid("getSelections");
        if (null == row || row.length == 0) {
            $.messager.alert('提示', '请选择一条信息！', 'info');
            return;
        }

        parent.$.modalDialog({
            title : '上链日志详情',
            width : 800,
            height : 480,
            href : projectPath + 'goUpdDemoLogPage?id='+row[0].id
        });
    }
</script>