<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'修改常量信息'" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="updConstantsForm" method="post">
                <input type="hidden" id="id" name="id" value="${constantsVO.id}"/>
                <input type="hidden" id="useFlag" name="useFlag" value="${constantsVO.deleteFlag}"/>
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">key值：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="key" name="key" type="text"
                                   value="${constantsVO.key }" data-options="validType:'length[0,50]'"/>
                        </td>
                        <td align="right">value值：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="value" name="value" type="text"
                                   value="${constantsVO.value }" data-options="validType:'length[0,100]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">描述：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="comments" name="comments" type="text"
                                   value="${constantsVO.comments }" data-options="validType:'length[0,100]'"/>
                        </td>
                        <td align="right">可用标记：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="deleteFlag" name="deleteFlag"
                                    style="width: 166px" data-options="editable:false,panelHeight:'auto'">
                                <option value="0">可用</option>
                                <option value="1">不可用</option>
                            </select>
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
        // 获取可用标记的值
        var useFlag = $("#useFlag").val();
        // 将值赋给可用标记
        $('#deleteFlag').val(useFlag);
    });

    // 修改常量信息
    function editData() {
        $.messager.confirm('提示', '确定要修改此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doUpdConstants";
                progressLoad();
                $("#updConstantsForm").form('submit',{
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
                            $.messager.alert('修改常量信息失败', data.msg, 'error');
                        }
                        progressClose();

                    }
                })
            }
        });
    }
</script>

