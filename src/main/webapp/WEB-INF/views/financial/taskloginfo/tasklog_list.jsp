<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchTaskLogForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'任务日志列表'">
        <table id="taskLogDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<script>
    var taskLogDataGrid ;
    $(function () {
        // 加载任务日志列表数据
        getTaskLogListData();

    });

    // 初始化任务日志列表数据
    function getTaskLogListData(){
        taskLogDataGrid = $('#taskLogDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getTaskLogList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'artifactId',
                title: 'artifactId',
                align: 'center',
                width: 100
            }, {
                field: 'txHash',
                title: '交易哈希值',
                align: 'center',
                width: 30
            }, {
                field: 'state',
                title: '状态标记',
                align: 'center',
                width: 30,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:green">失败</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">成功</span>';
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'message',
                title: '报错信息',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'className',
                title: '报错接口',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'dataSource',
                title: '数据源',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'modifyTime',
                title: '修改时间',
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

</script>