<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchLogForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'日志列表'">
        <table id="logDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<script>
    var logDataGrid ;
    $(function () {
        // 加载日志列表数据
        getLogListData();

    });

    // 初始化日志列表数据
    function getLogListData(){
        logDataGrid = $('#logDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getLogList',
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
                field: 'requestUser',
                title: '用户',
                align: 'center',
                width: 50
            }, {
                field: 'requestApi',
                title: '接口',
                align: 'center',
                width: 50
            }, {
                field: 'requestData',
                title: '请求数据',
                align: 'center',
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