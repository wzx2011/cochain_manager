<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchWalletTokenForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'钱包积分列表'">
        <table id="walletTokenDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<script>
    var walletTokenDataGrid ;
    $(function () {
        // 加载钱包积分列表数据
        getWalletTokenListData();

    });

    // 初始化钱包积分列表数据
    function getWalletTokenListData(){
        walletTokenDataGrid = $('#walletTokenDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getWalletTokenList',
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
                field: 'name',
                title: '通证名',
                align: 'center',
                width: 50
            }, {
                field: 'owner',
                title: '创建者id',
                align: 'center',
                width: 50
            }, {
                field: 'amount',
                title: '通证总数',
                align: 'center',
                width: 50
            }, {
                field: 'hash',
                title: '通证id',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'endTime',
                title: '结束时间',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'state',
                title: '状态标记',
                align: 'center',
                sortable: true,
                width: 50,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:green">无效</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">有效</span>';
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'tranhash',
                title: 'token上链的交易hash',
                align: 'center',
                sortable: true,
                width: 50
            }]],
            onLoadError: function () {

            }
        });
    }

</script>
