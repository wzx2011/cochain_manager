<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchContractForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'合约列表'">
        <table id="contractDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<div id="contractToolbar" style="display: none;">
    <shiro:hasPermission name="/goAddContractPage">
        <a onclick="addContractFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-purple'">新增</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/goUpdContractPage">
        <a onclick="editContractFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-pencil icon-purple'">修改</a>
    </shiro:hasPermission>
</div>

<script>
    var contractDataGrid ;
    $(function () {
        // 加载合约列表数据
        getContractListData();

    });

    // 初始化合约列表数据
    function getContractListData(){
        contractDataGrid = $('#contractDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getContractList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            toolbar: "#contractToolbar",
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'name',
                title: '应用名称',
                align: 'center',
                width: 20
            }, {
                field: 'code',
                title: '应用代码',
                align: 'center',
                width: 50
            }, {
                field: 'contractAddress',
                title: '合约地址',
                align: 'center',
                width: 100
            }, {
                field: 'contractGas',
                title: 'gas值',
                align: 'center',
                sortable: true,
                width: 30
            }, {
                field: 'walletFilePath',
                title: '钱包文件地址',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'web3Url',
                title: 'web3地址',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'walletPwd',
                title: '钱包密码',
                align: 'center',
                width: 20
            }, {
                field: 'attemptCount',
                title: '尝试次数',
                align: 'center',
                sortable: true,
                width: 20
            }, {
                field: 'sleepDuration',
                title: '睡眠间隔',
                align: 'center',
                width: 20
            }, {
                field: 'state',
                title: '状态标记',
                align: 'center',
                width: 20,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:green">app禁用</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">app启用</span>';
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'defaultUser',
                title: '默认用户',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'keyStorePath',
                title: 'keyStorePath',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'chainId',
                title: 'chainId',
                align: 'center',
                sortable: true,
                width: 20
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
    function searchContractFun() {
        contractDataGrid.datagrid('load', $.serializeObject($('#searchContractForm')));
    }
    // 清空
    function cleanContractFun() {
        $('#searchContractForm input').val('');
        $('#searchContractForm select').val('');
        $("#searchContractForm").form('clear');
        contractDataGrid.datagrid('load', {});
    }

    // 进入新增合约信息
    function addContractFun(){
        parent.$.modalDialog({
            title : '新增合约信息',
            width : 800,
            height : 480,
            href : projectPath + 'goAddContractPage'
        });
    }

    // 进入修改合约信息页面
    function editContractFun(){
        var row = contractDataGrid.datagrid("getSelections");
        if (null == row || row.length == 0) {
            $.messager.alert('提示', '请选择要修改的信息！', 'info');
            return;
        }
        parent.$.modalDialog({
            title : '修改合约信息',
            width : 800,
            height : 480,
            href : projectPath + 'goUpdContractPage?id='+row[0].id
        });
    }
</script>
