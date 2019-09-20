<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'token转移接口'" >
    <div data-options="region:'center',border:false" style="overflow: auto;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <h3 align="center">调用token转移接口</h3>
            <form id="baseForm" method="post">
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">tokenid：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="tranTokenId" name="tranTokenId"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                        <td align="right">amount：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="tranAmount" name="tranAmount"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                    </tr>

                    <tr>
                        <td align="right">fromid：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="tranFromId" name="tranFromId"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                        <td align="right">toid：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="tranToId" name="tranToId"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                    </tr>

                    <tr>
                        <td align="right">password：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="tranPassword" name="tranPassword"
                                    style="width: 171px" data-options="required:true" />
                        </td>
                        <td align="right"></td>
                        <td align="left"></td>
                    </tr>

                    <tr>
                        <td colspan="2">
                            <p id="tranMsg"></p>
                            <p id="tranResult"></p>
                        </td>
                    </tr>
                </table>
            </form>

            <table style="width: 100%; border-collapse: separate; border-spacing: 10px;">
                <tr>
                    <td colspan="4" align="center">
                        <a id="btnSubmit" onclick="submitTranData();" class="easyui-linkbutton" style="width: 75px; height: 25px">提交</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        
    });

    // 提交token转移接口信息
    function submitTranData() {
        $.messager.confirm('提示', '确定要提交此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doCallUserTranInterface";
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
                            $.messager.alert('调用token转移接口成功！', data.msg, 'success');
                            $("#tranMsg").html(data.msg);
                            $("#tranResult").html(data.objData);
                        } else {
                            $.messager.alert('调用token转移接口失败！', data.msg, 'error');
                        }
                        progressClose();
                    }
                });
            }
        });
    }
</script>
