<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchConstantsForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'常量列表'">
        <table id="constantsDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<div id="constantsToolbar" style="display: none;">
    <shiro:hasPermission name="/goAddConstantsPage">
        <a onclick="addConstantsFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-purple'">新增</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/goUpdConstantsPage">
        <a onclick="editConstantsFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-pencil icon-purple'">修改</a>
    </shiro:hasPermission>
</div>

<script>
    var constantsDataGrid ;
    $(function () {
        // 加载日志列表数据
        getConstantsListData();

    });

    // 初始化日志列表数据
    function getConstantsListData(){
        constantsDataGrid = $('#constantsDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getConstantsList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            toolbar: "#constantsToolbar",
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'key',
                title: 'key值',
                align: 'center',
                width: 50
            }, {
                field: 'value',
                title: 'value值',
                align: 'center',
                width: 50
            }, {
                field: 'comments',
                title: '描述',
                align: 'center',
                width: 50
            }, {
                field: 'deleteFlag',
                title: '可用标记',
                align: 'center',
                sortable: true,
                width: 50,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:green">可用</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">不可用</span>';
                    } else {
                        return value;
                    }
                }
            }]],
            onLoadError: function () {

            }
        });
    }

    // 查询
    function searchConstantsFun() {
        constantsDataGrid.datagrid('load', $.serializeObject($('#searchConstantsForm')));
    }
    // 清空
    function cleanConstantsFun() {
        $('#searchConstantsForm input').val('');
        $('#searchConstantsForm select').val('');
        $("#searchConstantsForm").form('clear');
        constantsDataGrid.datagrid('load', {});
    }

    // 进入新增常量信息
    function addConstantsFun(){
        parent.$.modalDialog({
            title : '新增常量信息',
            width : 800,
            height : 480,
            href : projectPath + 'goAddConstantsPage'
        });
    }

    // 进入修改常量信息页面
    function editConstantsFun(){
        var row = constantsDataGrid.datagrid("getSelections");
        if (null == row || row.length == 0) {
            $.messager.alert('提示', '请选择要修改的信息！', 'info');
            return;
        }
        parent.$.modalDialog({
            title : '修改常量信息',
            width : 800,
            height : 480,
            href : projectPath + 'goUpdConstantsPage?id='+row[0].id
        });
    }
</script>
