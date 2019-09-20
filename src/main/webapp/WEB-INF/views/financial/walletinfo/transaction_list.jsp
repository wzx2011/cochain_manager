<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchWalletTransactionForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'钱包交易列表'">
        <table id="walletTransactionDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<script>
    var walletTransactionDataGrid ;
    $(function () {
        // 加载钱包积分列表数据
        getWalletTransactionListData();

    });

    // 初始化钱包积分列表数据
    function getWalletTransactionListData(){
        walletTransactionDataGrid = $('#walletTransactionDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getWalletTransactionList',
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
                field: 'txHash',
                title: '交易hash',
                align: 'center',
                width: 100
            }, {
                field: 'fromid',
                title: '转出者id',
                align: 'center',
                width: 15
            }, {
                field: 'toid',
                title: '转入者id',
                align: 'center',
                width: 15
            }, {
                field: 'tokenid',
                title: '通证id',
                align: 'center',
                sortable: true,
                width: 15
            }, {
                field: 'amount',
                title: '通证数量',
                align: 'center',
                sortable: true,
                width: 15
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                sortable: true,
                width: 30
            }, {
                field: 'recordTime',
                title: '区块时间',
                align: 'center',
                sortable: true,
                width: 30
            }, {
                field: 'state',
                title: '数据状态',
                align: 'center',
                sortable: true,
                width: 20,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:green">未上链</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">上链未确认</span>';
                    } else if (value == 2) {
                        return '<span style="color:yellow">已确认上链</span>';
                    } else if (value == 3) {
                        return '<span style="color:red">上链失败</span>';
                    } else {
                        return value;
                    }
                }
            }]],
            onLoadError: function () {

            }
        });
    }

</script>
