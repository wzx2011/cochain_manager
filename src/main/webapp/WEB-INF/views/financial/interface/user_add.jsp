<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'创建钱包用户接口'" >
    <div data-options="region:'center',border:false" style="overflow: auto;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <h3 align="center">调用新增钱包用户接口</h3>
            <form id="baseForm" method="post">
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">uid：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="userUid" name="userUid"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                        <td align="right">password：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="userPassword" name="userPassword"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <p id="userMsg"></p>
                            <p id="userResult"></p>
                        </td>
                    </tr>
                </table>
            </form>


            <table style="width: 100%; border-collapse: separate; border-spacing: 10px;">
                <tr>
                    <td colspan="2" align="center">
                        <a id="btnSubmit" onclick="submitUserData();" class="easyui-linkbutton" style="width: 75px; height: 25px">提交</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        
    });

    // 提交用户接口信息
    function submitUserData() {
        $.messager.confirm('提示', '确定要提交此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doCallUserInterface";
                progressLoad();
                $("#baseForm").form('submit',{
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
                        if (data.code == '0001') {
                            $.messager.alert('调用新增钱包用户接口成功！', data.msg, 'success');
                            $("#userMsg").html(data.msg);
                            $("#userResult").html(data.objData);
                        } else {
                            $.messager.alert('调用新增钱包用户接口失败！', data.msg, 'error');
                        }
                        progressClose();
                    }
                });
            }
        });
    }
</script>

