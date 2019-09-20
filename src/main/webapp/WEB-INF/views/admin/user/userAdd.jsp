<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#userAddOrganizationId').combotree({
            url : '${path }/organization/tree',
            parentField : 'pid',
            panelHeight : 'auto',
            onBeforeSelect : function(node){
                if(node.id<0){
                    parent.$.messager.alert('提示', '请选择企业下的部门', 'warning');
                    $(this).tree("uncheck", node.target);//控制点击文字时也能勾选
                    return false;
                }
            }
        });

        $('#userAddRoleIds').combotree({
            url: '${path }/role/tree1',
            multiple: true,
            required: true,
            panelHeight : 'auto'
        });

        $('#userAddForm').form({
            url : '${path }/user/add',
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
                    var form = $('#userAddForm');
                    parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        <shiro:hasRole name="admin">
            checkDate();
        </shiro:hasRole>
    });
    function checkDate() {
        if($('#deadlineStr')){
            //结束时间 大于 当前日期
            $('#deadlineStr').datebox().datebox('calendar').calendar({
                validator: function(date){
                    var now = new Date();
                    var d = new Date(now.getFullYear(), now.getMonth(), now.getDate());
                    return d<=date;
                }
            });
        }
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="userAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>登录名</td>
                    <td><input name="loginName" type="text" placeholder="请输入登录名称" class="easyui-textbox"
                               data-options="validType:'length[4,20]',required:true" value=""></td>
                    <td>姓名</td>
                    <td><input name="name" type="text" placeholder="请输入姓名" class="easyui-textbox"
                               data-options="validType:'length[2,20]',required:true" value=""></td>
                </tr>
                <tr>
                    <td>密码</td>
                    <td><input name="password"  type="password" placeholder="请输入密码" class="easyui-textbox"
                               data-options="validType:'length[6,20]',required:true"></td>
                    <%--<td>性别</td>--%>
                    <%--<td>--%>
                        <%--<select name="sex" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">--%>
                            <%--<option value="0" selected="selected">男</option>--%>
                            <%--<option value="1" >女</option>--%>
                        <%--</select>--%>
                    <%--</td>--%>
                </tr>
                <tr>
                    <%--<td>年龄</td>
                    <td><input type="text" name="age" class="easyui-numberbox"/></td>--%>
                    <td>邮箱</td>
                    <td><input type="text"  class="easyui-textbox" validType='email' name="email" data-options="required:true"
                               invalidMessage="请输入正确的邮箱"/></td>
                    <td>用户类型</td>
                    <td>
                        <select name="userType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <%--<option value="0">管理员</option>--%>
                            <option value="1" selected="selected">用户</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>部门</td>
                    <td><select id="userAddOrganizationId" name="organizationId" style="width: 140px; height: 29px;" class="easyui-validatebox" data-options="required:true"></select></td>
                    <td>角色</td>
                    <td><select id="userAddRoleIds" name="roleIds" style="width: 140px; height: 29px;"></select></td>
                </tr>
                <tr>
                    <td>电话</td>
                    <td>
                        <input type="text" name="phone" class="easyui-numberbox"/>
                    </td>
                    <td>用户状态</td>
                    <td>
                        <select name="status" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                                <option value="0">正常</option>
                                <option value="1">停用</option>
                        </select>
                    </td>
                </tr>
                <shiro:hasRole name="admin">
                <tr>
                    <td>有效时间</td>
                    <td>
                        <input type="text" name="deadlineStr" id="deadlineStr" value="${user.deadline}"  class="easyui-datebox" placeholder="点击选择时间"   />
                    </td>
                </tr>
                </shiro:hasRole>
            </table>
        </form>
    </div>
</div>