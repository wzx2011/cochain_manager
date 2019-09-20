<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'修改用户状态信息'" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="updUserApiForm" method="post">
                <input type="hidden" id="id" name="id" value="${userApiVO.id}"/>
                <input type="hidden" id="userState" name="userState" value="${userApiVO.state}"/>
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">用户名：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="name" name="name" type="text"
                                   value="${userApiVO.name}" data-options="editable:false"/>
                        </td>
                        <td align="right">状态标记：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="state" name="state" style="width: 171px"></select>
                        </td>
                    </tr>
                </table>
            </form>

            <table style="width: 100%; border-collapse: separate; border-spacing: 10px;">
                <tr>
                    <td colspan="4" align="center">
                        <a id="btnSave" onclick="editData();" class="easyui-linkbutton" style="width: 75px; height: 25px">保存</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        // 初始化用户状态数据并赋值
        initUserStateList();
    });

    // 修改合约信息
    function editData() {
        $.messager.confirm('提示', '确定要修改此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doUpdUserApi";
                progressLoad();
                $("#updUserApiForm").form('submit',{
                    url: url,
                    //提交前验证
                    onSubmit:function(param){   //提交触发前的回调函数
                        progressLoad();
                        var isValid = $(this).form('validate');
                        if (!isValid) {
                            progressClose();
                        }
                        return isValid;
                    },
                    success : function(data) {
                        data = $.parseJSON(data);
                        if (data.info == true) {
                            parent.$.modalDialog.handler.dialog('close');
                            refreshTab();
                        } else {
                            $.messager.alert('修改用户状态信息失败', data.msg, 'error');
                        }
                        progressClose();
                    }
                })
            }
        });
    }

    // 初始化用户状态并赋值
    function initUserStateList(){
        // 用户状态初始值
        var state = $("#userState").val();
        var dataArray = [{"id":"0","text":"0"},{"id":"1","text":"1"},{"id":"2","text":"2"},{"id":"3","text":"3"}];
        $('#state').combobox({
            data : dataArray,
            valueField : 'id',
            textField : 'text',
            value : state,
            editable : false,
            panelWidth : 173,
            panelHeight : '80'
        });
    }
</script>
