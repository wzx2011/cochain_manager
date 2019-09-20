<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchTransactionPoolForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'交易数据池列表'">
        <table id="transactionPoolDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<div id="transactionPoolToolbar" style="display: none;">
    <shiro:hasPermission name="/goUpdTransactionPoolPage">
        <a onclick="editTransactionPoolFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-purple'">新增</a>
    </shiro:hasPermission>
</div>

<script>
    var transactionPoolDataGrid ;
    $(function () {
        // 加载用户列表数据
        getTransactionPoolListData();

    });

    // 初始化用户列表数据
    function getTransactionPoolListData(){
        transactionPoolDataGrid = $('#transactionPoolDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getTransactionPoolList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            toolbar: "#transactionPoolToolbar",
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'baseHash',
                title: '基础信息hash值',
                align: 'center',
                width: 60
            }, {
                field: 'hash',
                title: '交易信息value的hash值',
                align: 'center',
                width: 60
            }, {
                field: 'value',
                title: '基础信息字符串',
                align: 'center',
                sortable: true,
                width: 60
            }, {
                field: 'type',
                title: '交易信息类型',
                align: 'center',
                width: 30
            }, {
                field: 'user',
                title: '用户',
                align: 'center',
                width: 30
            }, {
                field: 'artifactId',
                title: 'artifact_id',
                align: 'center',
                sortable: true,
                width: 60
            }, {
                field: 'txHash',
                title: '交易哈希值',
                align: 'center',
                sortable: true,
                width: 30,
                hidden: true
            }, {
                field: 'state',
                title: '上链状态',
                align: 'center',
                sortable: true,
                width: 30,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:aqua">未上链</span>';
                    } else if (value == 1) {
                        return '<span style="color:green">上链未确认</span>';
                    } else if (value == 2) {
                        return '<span style="color:blue">已确认上链</span>';
                    } else if (value == 3) {
                        return '<span style="color:red">上链失败</span>';
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'appIndex',
                title: 'App接口数量',
                align: 'center',
                width: 20,
                hidden: true
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
                width: 50
            }]],
            onLoadError: function () {

            }
        });
    }

    // 查询
    function searchTransactionPoolFun() {
        transactionPoolDataGrid.datagrid('load', $.serializeObject($('#searchTransactionPoolForm')));
    }
    // 清空
    function cleanTransactionPoolFun() {
        $('#searchTransactionPoolForm input').val('');
        $('#searchTransactionPoolForm select').val('');
        $("#searchTransactionPoolForm").form('clear');
        transactionPoolDataGrid.datagrid('load', {});
    }

    // 修改交易池状态信息
    function editTransactionPoolFun(){
        var row = transactionPoolDataGrid.datagrid("getSelections");
        if (null == row || row.length == 0) {
            $.messager.alert('提示', '请选择要修改的信息！', 'info');
            return;
        }
        parent.$.modalDialog({
            title : '修改交易池状态信息',
            width : 800,
            height : 480,
            href : projectPath + 'goUpdTransactionPoolPage?id='+row[0].id
        });
    }
</script>