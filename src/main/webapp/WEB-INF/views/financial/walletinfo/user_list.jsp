<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchWalletUserForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'钱包用户列表'">
        <table id="walletUserDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<script>
    var walletUserDataGrid ;
    $(function () {
        // 加载钱包用户列表数据
        getWalletUserListData();

    });

    // 初始化钱包用户列表数据
    function getWalletUserListData(){
        walletUserDataGrid = $('#walletUserDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getWalletUserList',
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
                title: '用户名',
                align: 'center',
                width: 50
            }, {
                field: 'loginName',
                title: '用户登陆名',
                align: 'center',
                width: 50
            }, {
                field: 'password',
                title: '密码',
                align: 'center',
                width: 50
            }, {
                field: 'mobile',
                title: '手机',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'address',
                title: '密钥地址',
                align: 'center',
                width: 50
            }, {
                field: 'ks',
                title: 'ks',
                align: 'center',
                width: 50
            }, {
                field: 'split',
                title: '分割点',
                align: 'center',
                width: 50
            }, {
                field: 'state',
                title: '用户状态',
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
                field: 'fails',
                title: '登录失败次数',
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
            }, {
                field: 'lockTime',
                title: '锁定时间',
                align: 'center',
                sortable: true,
                width: 50
            }, {
                field: 'ipaddr',
                title: '登录ip',
                align: 'center',
                sortable: true,
                width: 50
            }]],
            onLoadError: function () {

            }
        });
    }

</script>
