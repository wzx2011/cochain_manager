<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchUserApiForm">
            <table>
                <tr>

                </tr>
            </table>
        </form>
    </div>
    <!-- data list -->
    <div data-options="region:'center',border:true,title:'应用用户列表'">
        <table id="userApiDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>

<div id="userApiToolbar" style="display: none;">
    <shiro:hasPermission name="/goAddUserApiPage">
        <a onclick="addUserApiFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-purple'">新增</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/goUpdUserApiPage">
        <a onclick="editUserApiFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-pencil icon-purple'">修改</a>
    </shiro:hasPermission>
</div>

<script>
    var userApiDataGrid ;
    $(function () {
        // 加载用户列表数据
        getUserApiListData();

    });

    // 初始化用户列表数据
    function getUserApiListData(){
        userApiDataGrid = $('#userApiDataGrid').datagrid({
            width: 'auto',
            height: 'auto',
            pageSize: 10,
            striped: true,
            singleSelect: true,
            url: projectPath + 'getUserApiList',
            loadMsg: '数据加载中请稍后...',
            pagination: true,
            fitColumns: true,
            fit: true,
            remoteSort: false,
            toolbar: "#userApiToolbar",
            columns: [[{
                field: 'id',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'name',
                title: '用户名',
                align: 'center',
                width: 60
            }, {
                field: 'loginName',
                title: '用户登陆名',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'password',
                title: '密码',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'mobile',
                title: '手机',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'telephone',
                title: '座机',
                align: 'center',
                width: 50,
                hidden: true
            }, {
                field: 'mail',
                title: '邮箱',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'token',
                title: '登陆令牌',
                align: 'center',
                sortable: true,
                width: 60
            }, {
                field: 'refreshTime',
                title: '令牌刷新时间',
                align: 'center',
                sortable: true,
                width: 30
            }, {
                field: 'expiresIn',
                title: '有效时间/秒',
                align: 'center',
                width: 20
            }, {
                field: 'keyStorePath',
                title: '密钥地址',
                align: 'center',
                sortable: true,
                width: 60
            }, {
                field: 'state',
                title: '状态标记',
                align: 'center',
                sortable: true,
                width: 20,
                formatter: function (value, rec) {
                    if (value == 0) {
                        return '<span style="color:blue">可用</span>';
                    } else if (value == 1) {
                        return '<span style="color:blue">可用</span>';
                    } else if (value == 2) {
                        return '<span style="color:blue">可用</span>';
                    } else if (value == 3) {
                        return '<span style="color:red">不可用</span>';
                    } else {
                        return value;
                    }
                }
            }, {
                field: 'modifyTime',
                title: '修改时间',
                align: 'center',
                sortable: true,
                width: 50,
                hidden: true
            }, {
                field: 'createTime',
                title: '创建时间',
                align: 'center',
                width: 50,
                hidden: true
            }]],
            onLoadError: function () {

            }
        });
    }

    // 查询
    function searchUserApiFun() {
        userApiDataGrid.datagrid('load', $.serializeObject($('#searchUserApiForm')));
    }
    // 清空
    function cleanUserApiFun() {
        $('#searchUserApiForm input').val('');
        $('#searchUserApiForm select').val('');
        $("#searchUserApiForm").form('clear');
        userApiDataGrid.datagrid('load', {});
    }

    // 进入新增用户页面
    function addUserApiFun(){
        parent.$.modalDialog({
            title : '新增用户',
            width : 800,
            height : 480,
            href : projectPath + 'goAddUserApiPage'
        });
    }

    // 进入修改应用用户状态页面
    function editUserApiFun(){
        var row = userApiDataGrid.datagrid("getSelections");
        if (null == row || row.length == 0) {
            $.messager.alert('提示', '请选择要修改的信息！', 'info');
            return;
        }
        parent.$.modalDialog({
            title : '修改用户状态信息',
            width : 800,
            height : 480,
            href : projectPath + 'goUpdUserApiPage?id='+row[0].id
        });
    }
</script>