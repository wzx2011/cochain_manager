<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        var roleTypes=${roleTypes};
        var html='';
        $.each(roleTypes,function (index,value) {
            if(value == '0'){
                html+="<option value=\"1\">系统管理员</option>";
            }
            if(value == '1'){
                html+="<option value=\"2\">普通用户</option>";
            }
        })
        $("#roleTypeAdd").html(html);

        $('#roleAddForm').form({
            url : '${path }/role/add',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                    var form = $('#roleAddForm');
                    parent.$.messager.alert('错误', eval(result.msg), 'error');
                }
            }
        });
    });
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="roleAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>角色名称</td>
                    <td><input name="name" type="text" placeholder="请输入角色名称" class="easyui-textbox span2" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>排序</td>
                    <td><input name="seq" value="0" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
                </tr>
                <tr>
                    <td>状态</td>
                    <td >
                        <select name="status" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="0">正常</option>
                            <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td colspan="3"><textarea name="description"></textarea></td>
                </tr>
                <tr>
                    <td>角色类型</td>
                    <td >
                        <select name="roleType" id="roleTypeAdd" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        </select>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>