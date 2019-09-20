<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'新增用户信息'" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="addUserApiForm" method="post">
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">应用：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="appId" name="appId"
                                    style="width: 166px" data-options="editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                        <td align="right">密钥：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="secretkey" name="secretkey" type="text"
                                   data-options="validType:'length[0,64]'" />
                        </td>
                    </tr>
                </table>

            </form>

            <table style="width: 100%; border-collapse: separate; border-spacing: 10px;">
                <tr>
                    <td colspan="4" align="center">
                        <a id="btnSave" onclick="saveData();" class="easyui-linkbutton" style="width: 75px; height: 25px">保存</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        // 应用名称下拉框赋值
        getTokenValue();

    });

    // 应用名称下拉框赋值
    function getTokenValue(){
        var url = projectPath +"getAppVOList";
        $.get(url,function (data) {
            $('#appId').combobox({
                data : data.listData,
                valueField:'id',
                textField:'name'
            });
            registerWindow();
        },'json');
    }

    // 新增用户信息
    function saveData() {
        $.messager.confirm('提示', '确定要添加此信息吗？', function(r) {
            var appId = $("#appId").combobox('getValue');
            var secretkey = $("#secretkey").textbox('getValue');
            if (r) {
                var url = projectPath + "doAddUserByCallInterface";
                progressLoad();
                $.post(url, {
                    appId : appId,
                    secretkey : secretkey
                }, function(result) {
                    if (result.code=="0001") {
                        parent.$.modalDialog.handler.dialog('close');
                        refreshTab();
                    } else {
                        $.messager.alert('失败', result.msg, 'error');
                    }
                    progressClose();
                }, 'json');
            }
        });
    }
</script>