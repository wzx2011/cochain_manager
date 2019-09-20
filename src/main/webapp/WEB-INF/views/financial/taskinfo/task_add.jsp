<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'新增任务信息'" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="addTaskForm" method="post">
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">任务名称：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="name" name="name" type="text"
                                   data-options="validType:'length[0,50]'"/>
                        </td>
                        <td align="right">任务代码：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="code" name="code" type="text"
                                   data-options="validType:'length[0,50]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">执行频率参数：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="corn" name="corn" type="text"
                                   data-options="validType:'length[0,50]'"/>
                        </td>
                        <td align="right">重试次数：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="retry" name="retry" type="text"
                                   value="0" data-options="validType:'length[0,4]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">睡眠间隔：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="sleep" name="sleep" type="text"
                                   value="0" data-options="validType:'length[0,4]'"/>
                        </td>
                        <td align="right">负责人电话：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="respPersonPhone" name="respPersonPhone" type="text"
                                   data-options="validType:'length[0,100]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">负责人姓名：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="respPersonName" name="respPersonName" type="text"
                                   data-options="validType:'length[0,100]'"/>
                        </td>
                        <td align="right">负责人邮箱：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="respPersonMail" name="respPersonMail" type="text"
                                   data-options="validType:'length[0,200]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">状态标记：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="state" name="state"
                                    style="width: 166px" data-options="editable:false,panelHeight:'auto'">
                                <option value="1">成功</option>
                                <option value="0">失败</option>
                            </select>
                        </td>
                        <td align="right">isOpen：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="isOpen" name="isOpen" type="text"
                                   data-options="validType:'length[0,1]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">isTaskExcute：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="isTaskExcute" name="isTaskExcute" type="text"
                                   data-options="validType:'length[0,1]'"/>
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

    });

    // 新增任务信息
    function saveData() {
        $.messager.confirm('提示', '确定要添加此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doAddTask";
                progressLoad();
                $("#addTaskForm").form('submit',{
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
                            $.messager.alert('添加任务信息失败', data.msg, 'error');
                        }
                        progressClose();
                    }
                });
            }
        });
    }
</script>

