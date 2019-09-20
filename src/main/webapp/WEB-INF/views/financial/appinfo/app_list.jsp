<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchAppForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'应用列表'">
        <table id="appDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<div id="appToolbar" style="display: none;">
    <shiro:hasPermission name="/goAddAppPage">
        <a onclick="addAppFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-purple'">新增</a>
    </shiro:hasPermission>
</div>

<script>
    var appDataGrid ;
    $(function () {
        // 加载日志列表数据
        getAppListData();

    });

    // 初始化日志列表数据
    function getAppListData(){
        appDataGrid = $('#appDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getAppList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            toolbar: "#appToolbar",
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'name',
                title: '应用名称',
                align: 'center',
                width: 30
            }, {
                field: 'contractId',
                title: '合约表id',
                align: 'center',
                width: 20
            }, {
                field: 'appid',
                title: '合约id',
                align: 'center',
                width: 50
            }, {
                field: 'appkey',
                title: '合约key',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'token',
                title: 'token',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'expire',
                title: '有效时长/秒',
                align: 'center',
                sortable: true,
                width: 20
            }, {
                field: 'url',
                title: '路径',
                align: 'center',
                sortable: true,
                width: 20
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                sortable: true,
                width: 40
            }, {
                field: 'modifyTime',
                title: '修改时间',
                align: 'center',
                sortable: true,
                width: 40
            }]],
            onLoadError: function () {

            }
        });
    }

    // 查询
    function searchAppFun() {
        appDataGrid.datagrid('load', $.serializeObject($('#searchAppForm')));
    }
    // 清空
    function cleanAppFun() {
        $('#searchAppForm input').val('');
        $('#searchAppForm select').val('');
        $("#searchAppForm").form('clear');
        appDataGrid.datagrid('load', {});
    }

    // 进入新增应用信息
    function addAppFun(){
        parent.$.modalDialog({
            title : '新增应用信息',
            width : 800,
            height : 480,
            href : projectPath + 'goAddAppPage'
        });
    }
</script>