<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'修改合约信息'" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="updContractForm" method="post">
                <input type="hidden" id="id" name="id" value="${contractVO.id}"/>
                <input type="hidden" id="appState" name="appState" value="${contractVO.state}"/>
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">应用名称：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="name" name="name" type="text"
                                   value="${contractVO.name}" data-options="validType:'length[0,50]'"/>
                        </td>
                        <td align="right">应用代码：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="code" name="code" type="text"
                                   value="${contractVO.code}" data-options="validType:'length[0,50]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">合约地址：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="contractAddress" name="contractAddress" type="text"
                                   value="${contractVO.contractAddress}" data-options="validType:'length[0,200]'"/>
                        </td>
                        <td align="right">gas值：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="contractGas" name="contractGas" type="text"
                                   value="${contractVO.contractGas}" data-options="validType:'length[0,20]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">钱包文件地址：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="walletFilePath" name="walletFilePath" type="text"
                                   value="${contractVO.walletFilePath}" data-options="validType:'length[0,500]'"/>
                        </td>
                        <td align="right">web3地址：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="web3Url" name="web3Url" type="text"
                                   value="${contractVO.web3Url}" data-options="validType:'length[0,500]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">钱包密码：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="walletPwd" name="walletPwd" type="text"
                                   value="${contractVO.walletPwd}" data-options="validType:'length[0,500]'"/>
                        </td>
                        <td align="right">尝试次数：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="attemptCount" name="attemptCount" type="text"
                                   value="${contractVO.attemptCount}" data-options="validType:'length[0,4]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">睡眠间隔：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="sleepDuration" name="sleepDuration" type="text"
                                   value="${contractVO.sleepDuration}" data-options="validType:'length[0,4]'"/>
                        </td>
                        <td align="right">状态标记：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="state" name="state"
                                    style="width: 166px" data-options="editable:false,panelHeight:'auto'">
                                <option value="1">app启用</option>
                                <option value="0">app禁用</option>
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">默认用户：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="defaultUser" name="defaultUser" type="text"
                                   value="${contractVO.defaultUser}" data-options="validType:'length[0,100]'"/>
                        </td>
                        <td align="right">keyStorePath：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="keyStorePath" name="keyStorePath" type="text"
                                   value="${contractVO.keyStorePath}" data-options="validType:'length[0,500]'"/>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">chainId：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="chainId" name="chainId" type="text"
                                   value="${contractVO.chainId}" data-options="validType:'length[0,4]'"/>
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
        var appState = $("#appState").val();
        // 将值赋给状态标记
        $('#state').val(appState);
    });

    // 修改合约信息
    function editData() {
        $.messager.confirm('提示', '确定要修改此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doUpdContract";
                progressLoad();
                $("#updContractForm").form('submit',{
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
                            $.messager.alert('修改合约信息失败', data.msg, 'error');
                        }
                        progressClose();
                    }
                })
            }
        });
    }
</script>


