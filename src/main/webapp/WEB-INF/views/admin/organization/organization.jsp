<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var organizationTreeGrid;
    // var enterpriseTree;
    $(function() {
       /* enterpriseTree = $('#enterpriseTree').tree({
            url : '${path }/getEnterpriseTree',
            parentField : 'pid',
            onClick : function(node) {
                organizationTreeGrid.datagrid('load', {
                    enterpriseId: node.id
                });
                $("#enterpriseId").val(node.id);
            }
        });*/
        organizationTreeGrid = $('#organizationTreeGrid').datagrid({
            url : '${path }/organization/treeGrid',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'id',
            sortName : 'createTime',
            sortOrder : 'asc',
            pageSize : 10,
            pageList : [ 10, 20, 30, 40, 50],
            frozenColumns : [ [ {
                title : 'id',
                field : 'id',
                width : 40,
                hidden : true
            } ] ],
            columns : [ [ {
                field : 'code',
                title : '编号',
                width : 40
            },{
                field : 'name',
                title : '部门名称',
                width : 180
            }, {
                field : 'seq',
                title : '排序',
                width : 40
            }, {
                field : 'iconCls',
                title : '图标',
                width : 120
            },  {
                width : '130',
                title : '创建时间',
                field : 'createTime'
            },{
                field : 'pid',
                title : '上级资源ID',
                width : 150,
                hidden : true
            }, {
                field : 'address',
                title : '地址',
                width : 120
            }, {
                field : 'enterpriseName',
                title : '企业',
                width : 120
            },{
                field : 'status',
                title : '状态',
                width : 80,
                formatter : function(value, row, index) {
                    if(value == 1) {
                        return '<span style="color:indigo">可用</span>';
                    }else if(value == 2) {
                        return '<span style="color:blue">不可用</span>';
                    }
                    return '<span style="color:gray">未知类型</span>';
                }
            }, {
                field : 'action',
                title : '操作',
                width : 130,
                formatter : function(value, row, index) {
                    var str = '';
                        <shiro:hasPermission name="/organization/edit">
                            str += $.formatString('<a href="javascript:void(0)" class="organization-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'glyphicon-pencil icon-blue\'" onclick="editOrganizationFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/organization/delete">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="organization-easyui-linkbutton-del" data-options="plain:true,iconCls:\'glyphicon-trash icon-red\'" onclick="deleteOrganizationFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.organization-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.organization-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#orgToolbar'
        });
    });
    
    function editOrganizationFun(id) {
        if (id != undefined) {
            organizationTreeGrid.treegrid('select', id);
        }
        var node = organizationTreeGrid.treegrid('getSelected');
        if (node) {
            if(node.status != 1){
                $.messager.alert('提示', '该状态下不可编辑！', 'info');
                return;
            }

            parent.$.modalDialog({
                title : '编辑',
                width : 500,
                height : 300,
                href : '${path }/organization/editPage?id=' + node.id,
                buttons : [ {
                    text : '编辑',
                    handler : function() {
                        parent.$.modalDialog.openner_treeGrid = organizationTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#organizationEditForm');
                        f.submit();
                    }
                } ]
            });
        }
    }
    
    function deleteOrganizationFun(id) {
        if (id != undefined) {
            organizationTreeGrid.treegrid('select', id);
        }
        var node = organizationTreeGrid.treegrid('getSelected');
        if (node) {
            if(node.status != 1){
                $.messager.alert('提示', '该状态下不可删除！', 'info');
                return;
            }
            parent.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
                if (b) {
                    progressLoad();
                    $.post('${path }/organization/delete', {
                        id : node.id
                    }, function(result) {
                        result = $.parseJSON(result);
                        if (result.success) {
                            parent.$.messager.alert('提示', result.msg, 'info');
                            organizationTreeGrid.treegrid('reload');
                        }else{
                            parent.$.messager.alert('提示', result.msg, 'info');
                        }
                        progressClose();
                    }, 'text');
                }
            });
        }
    }
    
    function addOrganizationFun() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path }/organization/addPage',
            buttons : [ {
                text : '添加',
                handler : function() {
                    parent.$.modalDialog.openner_treeGrid = organizationTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#organizationAddForm');
                    f.submit();
                }
            } ]
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <form id="searchOrganizationForm">
        <input type="hidden" name="enterpriseId" id="enterpriseId"/>
    </form>
    <div data-options="region:'center',border:false,title:'部门管理'"  style="overflow: hidden;">
        <table id="organizationTreeGrid"></table>
    </div>
    <div data-options="region:'west',border:true,split:false,title:'企业信息'"  style="width:150px;overflow: scroll; ">
        <%--<ul id="enterpriseTree" style="width:160px;margin: 10px 10px 10px 10px"></ul>--%>
    </div>
    <div id="orgToolbar" style="display: none;">
        <shiro:hasPermission name="/organization/add">
            <a onclick="addOrganizationFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'glyphicon-plus icon-green'">添加</a>
        </shiro:hasPermission>
    </div>
</div>