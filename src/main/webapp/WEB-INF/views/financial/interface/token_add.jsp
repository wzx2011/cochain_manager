<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'发放token接口'" >
    <div data-options="region:'center',border:false" style="overflow: auto;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <h3 align="center">调用发放token接口</h3>
            <form id="baseForm" method="post">
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">accountId：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="tokenAccountId" name="tokenAccountId"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                        <td align="right">password：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="tokenPassword" name="tokenPassword"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                    </tr>

                    <tr>
                        <td align="right">amount：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="tokenAmount" name="tokenAmount"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                        <td align="right">endTime：</td>
                        <td align="left">
                            <input class="easyui-datebox" id="tokenEndTime" name="tokenEndTime"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <p id="tokenMsg"></p>
                            <p id="tokenResult"></p>
                        </td>
                    </tr>
                </table>
            </form>

            <table style="width: 100%; border-collapse: separate; border-spacing: 10px;">
                <tr>
                    <td colspan="4" align="center">
                        <a id="btnSubmit" onclick="submitTokenData();" class="easyui-linkbutton" style="width: 75px; height: 25px">提交</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
      
    });

    // 提交token接口信息
    function submitTokenData() {
        $.messager.confirm('提示', '确定要提交此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doCallUserTokenInterface";
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
                            $.messager.alert('调用发放token接口成功！', data.msg, 'success');
                            $("#tokenMsg").html(data.msg);
                            $("#tokenResult").html(data.objData);
                        } else {
                            $.messager.alert('调用发放token接口失败！', data.msg, 'error');
                        }
                        progressClose();
                    }
                });
            }
        });
    }
</script>
