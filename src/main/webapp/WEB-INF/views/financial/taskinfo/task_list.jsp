<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchTaskForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'任务列表'">
        <table id="taskDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<div id="taskToolbar" style="display: none;">
    <shiro:hasPermission name="/goAddTaskPage">
        <a onclick="addTaskFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-purple'">新增</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/goUpdTaskPage">
        <a onclick="editTaskFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-pencil icon-purple'">修改</a>
    </shiro:hasPermission>
</div>

<script>
    var taskDataGrid ;
    $(function () {
        // 加载任务列表数据
        getTaskListData();

    });

    // 初始化任务列表数据
    function getTaskListData(){
        taskDataGrid = $('#taskDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getTaskList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            toolbar: "#taskToolbar",
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'name',
                title: '任务名称',
                align: 'center',
                width: 60
            }, {
                field: 'code',
                title: '任务代码',
                align: 'center',
                width: 80
            }, {
                field: 'corn',
                title: '执行频率参数',
                align: 'center',
                sortable: true,
                width: 40
            }, {
                field: 'retry',
                title: '重试次数',
                align: 'center',
                width: 40
            }, {
                field: 'sleep',
                title: '睡眠间隔',
                align: 'center',
                width: 40
            }, {
                field: 'respPersonPhone',
                title: '负责人电话',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'respPersonName',
                title: '负责人姓名',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'respPersonMail',
                title: '负责人邮箱',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'state',
                title: '是否启用',
                align: 'center',
                sortable: true,
                width: 40,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:green">否</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">是</span>';
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'isOpen',
                title: 'isOpen',
                align: 'center',
                sortable: true,
                width: 40
            }, {
                field: 'isTaskExcute',
                title: 'isTaskExcute',
                align: 'center',
                sortable: true,
                width: 40
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                width: 60
            }]],
            onLoadError: function () {

            }
        });
    }

    // 查询
    function searchTaskFun() {
        taskDataGrid.datagrid('load', $.serializeObject($('#searchTaskForm')));
    }
    // 清空
    function cleanTaskFun() {
        $('#searchTaskForm input').val('');
        $('#searchTaskForm select').val('');
        $("#searchTaskForm").form('clear');
        taskDataGrid.datagrid('load', {});
    }

    // 进入新增任务信息
    function addTaskFun(){
        parent.$.modalDialog({
            title : '新增任务信息',
            width : 800,
            height : 480,
            href : projectPath + 'goAddTaskPage'
        });
    }

    // 进入修改任务信息页面
    function editTaskFun(){
        var row = taskDataGrid.datagrid("getSelections");
        if (null == row || row.length == 0) {
            $.messager.alert('提示', '请选择要修改的信息！', 'info');
            return;
        }
        parent.$.modalDialog({
            title : '修改任务信息',
            width : 800,
            height : 480,
            href : projectPath + 'goUpdTaskPage?id='+row[0].id
        });
    }
</script>
