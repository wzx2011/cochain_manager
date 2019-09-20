
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'修改任务信息'" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="updTaskForm" method="post">
                <input type="hidden" id="id" name="id" value="${taskVO.id}"/>
                <input type="hidden" id="taskState" name="taskState" value="${taskVO.state}"/>
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">任务名称：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="name" name="name" type="text"
                                   value="${taskVO.name}" data-options="validType:'length[0,50]'"/>
                        </td>
                        <td align="right">任务代码：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="code" name="code" type="text"
                                   value="${taskVO.code}" data-options="validType:'length[0,50]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">执行频率参数：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="corn" name="corn" type="text"
                                   value="${taskVO.corn}" data-options="validType:'length[0,50]'"/>
                        </td>
                        <td align="right">重试次数：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="retry" name="retry" type="text"
                                   value="${taskVO.retry}" data-options="validType:'length[0,4]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">睡眠间隔：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="sleep" name="sleep" type="text"
                                   value="${taskVO.sleep}" data-options="validType:'length[0,4]'"/>
                        </td>
                        <td align="right">负责人电话：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="respPersonPhone" name="respPersonPhone" type="text"
                                   value="${taskVO.respPersonPhone}" data-options="validType:'length[0,100]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">负责人姓名：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="respPersonName" name="respPersonName" type="text"
                                   value="${taskVO.respPersonName}" data-options="validType:'length[0,100]'"/>
                        </td>
                        <td align="right">负责人邮箱：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="respPersonMail" name="respPersonMail" type="text"
                                   value="${taskVO.respPersonMail}" data-options="validType:'length[0,200]'"/>
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
                                   value="${taskVO.isOpen}" data-options="validType:'length[0,1]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">isTaskExcute：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="isTaskExcute" name="isTaskExcute" type="text"
                                   value="${taskVO.isTaskExcute}" data-options="validType:'length[0,1]'"/>
                        </td>
                        <td align="right"></td>
                        <td align="left"></td>
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
        // 获取状态标记的值
        var taskState = $("#taskState").val();
        // 将值赋给状态标记
        $('#state').val(taskState);
    });

    // 修改任务信息
    function editData() {
        $.messager.confirm('提示', '确定要修改此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doUpdTask";
                progressLoad();
                $("#updTaskForm").form('submit',{
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
                            $.messager.alert('修改任务信息失败', data.msg, 'error');
                        }
                        progressClose();
                    }
                })
            }
        });
    }
</script>


