<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var resourceTree;
    var eventNode ;
    $(function() {
        var roleTreeCheck = true;
        var isUserCheck = true;
        resourceTree = $('#resourceTree').tree({
            url : '${path }/resource/allShiroTrees',
            parentField : 'pid',
            checkbox : true,
            cascadeCheck: false,
            onCheck : function(node, checked) {
                var tree = $('#resourceTree');
                if (!eventNode) {//鼠标勾选事件初始化标致，标识鼠标勾选事件第一次遍历开始
                    eventNode = node;//将鼠标勾选时的节点保存起来，待向下 遍历时，将从此节点开始
                    treeup = true;//向上遍历标志，由鼠标勾选的节点eventNode开始向上遍历
                }
                if (treeup) {//向上遍历
                    var ParentNode = tree.tree("getParent", node.target);
                    if (ParentNode) {//存在上级节点
                        if (checked)//如果是选择
                            tree.tree("check", ParentNode.target);//选中上级节点。注意：如果父节点之前未选中，执行此行代码后，会再次触发onCheck事件，下面的代码暂时不会被执行；如果之前兄弟节点已被选中，那么上级节点也被选中，此行代码执行后不会触发onCheck事件，而直接执行下面的代码
                        else {//如果是取消选择
                            var isCheck = false;
                            var childNode = tree.tree("getLeafChildren", ParentNode.target);
                            for (var i = 0; i < childNode.length; i++) {//循环当前节点的父节点的所有子节点，及包含当前节点的所有兄弟节点
                                if (childNode[i].checked) {
                                    isCheck = true;//只要有兄弟节点被选中，则退出循环
                                    break;
                                }
                            }
                            if (!isCheck)//如果所有兄弟节点及当前节点都未勾选，则取消父节点的勾选
                                tree.tree("uncheck", ParentNode.target);
                        }
                    }
                    treeup = false;//向上遍历结束
                }
                //到达根部节点或向上遍历结束之后再回到原始节点开始向下遍历
                if (!treeup && eventNode) {
                    var childNode = tree.tree("getChildren", eventNode.target);//获取原始节点eventNode的所有子孙节点
                    if (checked) {//如果是选择
                        for (var i = 0; i < childNode.length; i++) {//循环所有子孙节点，全部选中
                            tree.tree("check", childNode[i].target);
                        }
                    }
                    else {//如果是取消
                        for (var i = 0; i < childNode.length; i++) {//循环所有子孙节点，全部取消勾选
                            tree.tree("uncheck", childNode[i].target);
                        }
                    }
                }
                eventNode = null;//标志本次鼠标勾选事件遍历结束
            },
            onLoadSuccess : function(node, data) {
                progressLoad();
                $.post( '${path }/role/findResourceIdListByRoleId', {
                    id : '${id}'
                }, function(result) {
                    var ids;
                    if (result.success == true && result.obj != undefined) {
                        ids = $.stringToList(result.obj + '');
                    }
                    if (ids.length > 0) {
                        for ( var i = 0; i < ids.length; i++) {
                            if (resourceTree.tree('find', ids[i])) {
                                resourceTree.tree('check', resourceTree.tree('find', ids[i]).target);
                            }else{
                                resourceTree.tree('uncheck', resourceTree.tree('find', ids[i]).target);
                            }
                        }
                    }
                }, 'json');
                progressClose();
            }

        });
        $.extend($.fn.tree.methods, {
            getLeafChildren: function (jq, params) {
                var nodes = [];
                $(params).next().children().children("div.tree-node").each(function () {
                    nodes.push($(jq[0]).tree('getNode', this));
                });
                return nodes;
            }
        });
        $('#roleGrantForm').form({
            url : '${path }/role/grant',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                var checknodes = resourceTree.tree('getChecked');
                var ids = [];
                if (checknodes && checknodes.length > 0) {
                    for ( var i = 0; i < checknodes.length; i++) {
                        ids.push(checknodes[i].id);
                    }
                }
                $('#resourceIds').val(ids);
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    parent.$.messager.alert('错误', result.msg, 'error');
                }
            }
        });
    });

    // 全选
    function checkAll() {
        var nodes = resourceTree.tree('getChecked', 'unchecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('check', nodes[i].target);
            }
        }
    }
    // 取消
    function uncheckAll() {
        var nodes = resourceTree.tree('getChecked');
        if (nodes && nodes.length > 0) {
            for ( var i = 0; i < nodes.length; i++) {
                resourceTree.tree('uncheck', nodes[i].target);
            }
        }
    }
    // 反选
    function checkInverse() {
        var unchecknodes = resourceTree.tree('getChecked', 'unchecked');
        var checknodes = resourceTree.tree('getChecked');
        if (unchecknodes && unchecknodes.length > 0) {
            for ( var i = 0; i < unchecknodes.length; i++) {
                resourceTree.tree('check', unchecknodes[i].target);
            }
        }
        if (checknodes && checknodes.length > 0) {
            for ( var i = 0; i < checknodes.length; i++) {
                resourceTree.tree('uncheck', checknodes[i].target);
            }
        }
    }
</script>
<div id="roleGrantLayout" class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'west'" title="系统资源" style="width: 300px; padding: 1px;">
        <div class="well well-small">
            <form id="roleGrantForm" method="post">
                <input name="id" type="hidden"  value="${id}" readonly="readonly">
                <div class="easyui-panel"
                     style="width:100%;height:320px;border:0px;padding:10px 10px 10px 0px;">
                    <ul id="resourceTree" class="easyui-tree"></ul>
                </div>
                <input id="resourceIds" name="resourceIds" type="hidden" />
            </form>
        </div>
    </div>
    <div data-options="region:'center'" title="" style="overflow: hidden; padding: 10px;">
        <div>
            <button class="btn btn-success" onclick="checkAll();">全选</button>
            <br /> <br />
            <button class="btn btn-warning" onclick="checkInverse();">反选</button>
            <br /> <br />
            <button class="btn btn-inverse" onclick="uncheckAll();">取消</button>
        </div>
    </div>
</div>