<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchUserForm">
            <input type="hidden" name="organizationId" id="organizationId"/>
            <table>
                <tr>
                    <th>姓名：</th>
                    <td><input class="easyui-textbox" name="name" /></td>
                    <th>&nbsp;状态：</th>
                    <td>
                        <select class="easyui-combobox" id="status" name="status"
                                style="width: 171px" data-options="editable:false,panelHeight:'auto'">
                            <option value="">请选择</option>
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                            <shiro:hasRole name="admin">
                                <option value="2">待审核</option>
                            </shiro:hasRole>
                        </select>
                    </td>
                    <th>&nbsp;创建时间：</th>
                    <td>
                        <input class="easyui-datebox" id="createdateStart" name="createdateStartStr" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" type="text"/>&nbsp;至&nbsp;
                        <input class="easyui-datebox" id="createdateEnd" name="createdateEndStr" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" type="text"/>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-search',plain:true" onclick="searchUserFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'glyphicon-remove-circle',plain:true" onclick="cleanUserFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'用户列表'" >
        <table id="userDataGrid" data-options="fit:true,border:false"></table>
    </div>
    <div data-options="region:'west',border:true,split:false,title:'组织机构'"  style="width:150px;overflow: scroll; ">
        <ul id="organizationTree" style="width:160px;margin: 10px 10px 10px 10px"></ul>
    </div>
</div>
<div id="userToolbar" style="display: none;">
    <shiro:hasPermission name="/user/add">
        <a onclick="addUserFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/user/edit">
        <a href="javascript:void(0)" onclick="editUserFun();"  class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-pencil icon-blue'" >编辑</a>
    </shiro:hasPermission>
    <shiro:hasPermission name="/user/delete">
        <a href="javascript:void(0)" onclick="deleteUserFun();"  class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-trash icon-red'" >删除</a>
    </shiro:hasPermission>
</div>

<script type="text/javascript">
    var userDataGrid;
    var organizationTree;

    $(function() {
        organizationTree = $('#organizationTree').tree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            onClick : function(node) {
                userDataGrid.datagrid('load', {
                    organizationId: node.id
                });
                $("#organizationId").val(node.id);
            }
        });

        userDataGrid = $('#userDataGrid').datagrid({
            url : '${path }/user/dataGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            sortName : 'createTime',
	        sortOrder : 'asc',
            pageSize : 15,
            pageList : [ 10, 20, 30, 40, 50],
            columns : [ [ {
                width : '80',
                title : '登录名',
                field : 'loginName',
                sortable : true
            }, {
                width : '80',
                title : '姓名',
                field : 'name',
                sortable : true
            },{
                width : '80',
                title : '部门ID',
                field : 'organizationId',
                hidden : true
            },{
                width : '80',
                title : '所属部门',
                field : 'organizationName'
            },{
                width : '130',
                title : '创建时间',
                field : 'createTime',
                sortable : true
            }, {
                width : '130',
                title : '有效时间',
                field : 'deadline',
                sortable : true
            }, {
                width : '40',
                title : '性别',
                field : 'sex',
                sortable : true,
                hidden : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '<span style="color:blue">男</span>';
                    case 1:
                        return '<span style="color:deeppink">女</span>';
                    }
                }
            }, {
                width : '40',
                title : '年龄',
                field : 'age',
                sortable : true,
                hidden : true
            },{
                width : '120',
                title : '电话',
                field : 'phone',
                sortable : true
            },
            {
                width : '120',
                title : '邮箱',
                field : 'email',
                sortable : true
            },
            {
                width : '200',
                title : '角色',
                field : 'rolesList'
            }, {
                width : '60',
                title : '用户类型',
                field : 'userType',
                sortable : true,
                formatter : function(value, row, index) {
                    if(value == 0) {
                        return '<span style="color:indigo">管理员</span>';
                    }else if(value == 1) {
                        return '<span style="color:blue">用户</span>';
                    }
                    return '<span style="color:gray">未知类型</span>';
                }
            },{
                width : '60',
                title : '状态',
                field : 'status',
                sortable : true,
                formatter : function(value, row, index) {
                    switch (value) {
                    case 0:
                        return '<span style="color:blue">正常</span>';
                    case 1:
                        return '<span style="color:gray">停用</span>';
                    case 2:
                        return '<span style="color:deeppink">待审核</span>';
                    }
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.user-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#userToolbar'
        });
        checkUserDate();
    });

    function addUserFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 320,
            href : '${path }/user/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userAddForm');
                    f.submit();
                }
            } ]
        });
    }

    function deleteUserFun(id) {
        var rows =userDataGrid.datagrid('getSelections');
        if (null == rows || rows.length == 0) {
            $.messager.alert('提示', '请选择要删除的用户！', 'info');
            return;
        }
        id = rows[0].id;
        parent.$.messager.confirm('询问', '您是否要删除当前用户？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/user/delete', {
                    id : id
                }, function(result) {
                    result = $.parseJSON(result);
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        userDataGrid.datagrid('reload');
                    } else {
                        parent.$.messager.alert('错误', result.msg, 'error');
                    }
                    progressClose();
                }, 'text');
            }
        });
    }

    function editUserFun(id) {
        var rows =userDataGrid.datagrid('getSelections');
        if (null == rows || rows.length == 0) {
            $.messager.alert('提示', '请选择要编辑的用户！', 'info');
            return;
        }
        id = rows[0].id;
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 340,
            href : '${path }/user/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = userDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#userEditForm');
                    f.submit();
                }
            } ]
        });
    }

    function searchUserFun() {
        userDataGrid.datagrid('load', $.serializeObject($('#searchUserForm')));
    }
    function cleanUserFun() {
        $('#searchUserForm input').val('');
        $("#searchUserForm").form('clear');
        userDataGrid.datagrid('load', {});
        $('#organizationTree').find('.tree-node-selected').removeClass('tree-node-selected');
    }
    function checkUserDate() {
        //结束时间 大于 开始日期
        $("#createdateStart").datebox({
            onSelect : function(beginDate){
                $('#createdateEnd').datebox().datebox('calendar').calendar({
                    validator: function(date){
                        return beginDate < date ;
                    }
                });
            }
        });
    }
</script>
