<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'基础信息上链'" >
    <div data-options="region:'center',border:false" style="overflow: auto;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="baseForm" method="post">
                <input type="hidden" id="urlTypeB" value="0"/>
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">应用：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="appNameB" name="appNameB"
                                    style="width: 300px" data-options="required:true,editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                        <td align="right">用户：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="userInfoB" name="userInfoB"
                                   style="width: 300px" data-options="required:true,editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">远程IP端口：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="ipPortB" name="ipPortB"
                                    style="width: 300px" data-options="required:true,editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                        <td align="right">项目：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="projectValueB" name="projectValueB"
                                   style="width: 300px" data-options="required:true,editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">上链路径：</td>
                        <td align="left">
                            <input class="easyui-textbox" id="projectUrlB" name="projectUrlB"
                                   style="width: 300px" data-options="required:true,editable:true">
                        </td>
                        <td align="right"></td>
                        <td align="left"></td>
                    </tr>

                    <tr>
                        <td align="right">参数：</td>
                        <td align="left" colspan="2">
                            <textarea id="parameterB" name="parameterB"
                                      style="width: 600px; height: 600px;" data-options="required:true"></textarea>
                        </td>
                        <td align="left">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <p id="uidStrB"></p>
                            <p id="secretkeyStrB"></p>
                            <p id="appidStrB"></p>
                            <p id="appkeyStrB"></p>
                            <input type="hidden" id="appidB" name="appidB" />
                            <input type="hidden" id="appkeyB" name="appkeyB" />
                            <input type="hidden" id="uidB" name="uidB" />
                            <input type="hidden" id="secretkeyB" name="secretkeyB" />
                            <p id="baseMsg"></p>
                            <p id="baseResult"></p>
                        </td>
                    </tr>
                </table>

            </form>

            <table style="width: 100%; border-collapse: separate; border-spacing: 10px;">
                <tr>
                    <td colspan="4" align="center">
                        <a id="btnSubmit" onclick="submitBaseData();" class="easyui-linkbutton" style="width: 75px; height: 25px">提交</a> &nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                </tr>
            </table>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        // 应用下拉框赋值
        getAppValue();

        // 远程IP端口下拉框赋值
        getIpPortValue();

        // 项目下拉框赋值
        getProjectValue();

        // select选择框触发事件
        $("#appNameB").combobox({
            //相当于html >> select >> onChange事件
            onChange: function () {
                var appid = $("#appNameB").combobox('getValue');
                $("#appidStrB").html("appid:"+appid.split(",")[0]);
                $("#appkeyStrB").html("appkey:"+appid.split(",")[1]);
                $("#appidB").val(appid.split(",")[0]);
                $("#appkeyB").val(appid.split(",")[1]);
                if (checkNull(appid)) {
                    $("#userInfoB").val('');
                } else {
                    $.post(projectPath + "getUserApiVOList", {
                        "appid": appid.split(",")[0]
                    }, function (data) {
                        if (data.code == '0001') {
                            $('#userInfoB').combobox({
                                data : data.listData,
                                valueField:'secretkey',
                                textField:'name'
                            });
                        } else {
                            $.messager.alert('失败', data.msg, 'error');
                        }
                    }, "json");
                }
            }
        });

        // select选择框触发事件
        $("#userInfoB").combobox({
            //相当于html >> select >> onChange事件
            onChange: function () {
                var secretkey = $("#userInfoB").combobox('getValue');
                var uid = $('#userInfoB').combobox('getText');
                if (checkNull(secretkey)) {
                    $("#userInfoB").val('');
                } else {
                    $("#uidStrB").html("uid:"+uid);
                    $("#secretkeyStrB").html("secretkey:"+secretkey);
                    $("#uidB").val(uid);
                    $("#secretkeyB").val(secretkey);
                }

            }
        });

        // select选择框触发事件
        $("#projectValueB").combobox({
            //相当于html >> select >> onChange事件
            onChange: function () {
                var projectValue = $("#projectValueB").combobox('getValue');
                var urlType = $("#urlTypeB").val();
                if (checkNull(projectValue)) {
                    $("#projectUrlB").val('');
                } else {
                    $.post(projectPath + "getDemoUrlVOList", {
                        "projectValue": projectValue,
                        "urlType": urlType
                    }, function (data) {
                        if (data.code == '0001') {
                            $("#projectUrlB").textbox('setValue', data.listData[0].projectUrl);
                        } else {
                            $.messager.alert('失败', data.msg, 'error');
                        }
                    }, "json");
                }
            }
        });
    });

    // 应用下拉框赋值
    function getAppValue(){
        var url = projectPath +"getAppVOList";
        $.get(url,function (data) {
            $('#appNameB').combobox({
                data : data.listData,
                valueField:'appid',
                textField:'name'
            });
        },'json');
    }

    // 远程IP端口下拉框赋值
    function getIpPortValue(){
        var url = projectPath +"getDemoIpVOList";
        $.get(url,function (data) {
            $('#ipPortB').combobox({
                data : data.listData,
                valueField:'ipPort',
                textField:'ipPort'
            });
        },'json');
    }

    // 项目下拉框赋值
    function getProjectValue(){
        var url = projectPath +"getDemoProjectVOList";
        $.get(url,function (data) {
            $('#projectValueB').combobox({
                data : data.listData,
                valueField:'projectValue',
                textField:'projectValue'
            });
        },'json');
    }

    // 提交接口信息
    function submitBaseData() {
        $.messager.confirm('提示', '确定要提交此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doBaseToChainInfo";
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
                            $.messager.alert('基础信息上链成功！', data.msg, 'success');
                            $("#baseMsg").html(data.msg);
                            $("#baseResult").html(data.objData);
                        } else {
                            $.messager.alert('基础信息上链失败！', data.msg, 'error');
                        }
                        progressClose();
                    }
                });
            }
        });
    }
</script>
