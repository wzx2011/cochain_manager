<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'新增应用信息'" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="addAppForm" method="post">
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">应用名称：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="name" name="name" type="text"
                                   data-options="validType:'length[0,50]'"/>
                        </td>
                        <td align="right">有效时长(秒)：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="expire" name="expire" type="text"
                                   data-options="validType:'length[0,11]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">合约表名称：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="contractId" name="contractId"
                                    style="width: 166px" data-options="editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                        <td align="right"></td>
                        <td align="left"></td>
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
        // 合约表名称下拉框赋值
        getContractIdValue();

    });

    // 合约表名称下拉框赋值
    function getContractIdValue(){
        var url = projectPath +"getContractVOList";
        $.get(url,function (data) {
            $('#contractId').combobox({
                data : data.clist,
                valueField:'id',
                textField:'name'
            });
            registerWindow();
        },'json');
    }

    // 新增应用信息
    function saveData() {
        $.messager.confirm('提示', '确定要添加此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doAddApp";
                progressLoad();
                $("#addAppForm").form('submit',{
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
                            $.messager.alert('添加应用信息失败', data.msg, 'error');
                        }
                        progressClose();
                    }
                });
            }
        });
    }
</script>

