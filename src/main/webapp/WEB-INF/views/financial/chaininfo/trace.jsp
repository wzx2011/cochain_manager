<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>

<!-- Content -->
<div  class="easyui-layout" data-options="fit:true,border:false,title:'交易信息上链'" >
    <div data-options="region:'center',border:false" style="overflow: auto;padding: 3px;" >
        <!-- data list -->
        <div data-options="region:'center'">
            <form id="traceForm" method="post">
                <input type="hidden" id="urlTypeT" value="1"/>
                <table style="width: 100%; border-collapse: separate; border-spacing: 20px;">
                    <tr>
                        <td align="right">应用：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="appNameT" name="appNameT"
                                    style="width: 300px" data-options="required:true,editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                        <td align="right">用户：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="userInfoT" name="userInfoT"
                                    style="width: 300px" data-options="required:true,editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">远程IP端口：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="ipPortT" name="ipPortT"
                                    style="width: 300px" data-options="required:true,editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                        <td align="right">项目：</td>
                        <td align="left">
                            <select class="easyui-combobox" id="projectValueT" name="projectValueT"
                                    style="width: 300px" data-options="required:true,editable:false,panelHeight:'auto'">
                            </select>
                        </td>
                    </tr>

                    <tr>
                        <td align="right">上链路径：</td>
                        <td align="left"  colspan="3">
                            <input class="easyui-textbox" id="projectUrlT" name="projectUrlT"
                                   style="width: 600px" data-options="required:true,editable:true">
                        </td>
                    </tr>

                    <tr>
                        <td align="right">参数：</td>
                        <td align="left" colspan="2">
                            <textarea id="parameterT" name="parameterT"
                                      style="width: 400px; height: 300px;" data-options="required:true"></textarea>
                        </td>
                        <td align="left">
                        </td>
                    </tr>
                    <tr>
                        <td colspan="4">
                            <p id="uidStrT"></p>
                            <p id="secretkeyStrT"></p>
                            <p id="appidStrT"></p>
                            <p id="appkeyStrT"></p>
                            <input type="hidden" id="appidT" name="appidT" />
                            <input type="hidden" id="appkeyT" name="appkeyT" />
                            <input type="hidden" id="uidT" name="uidT" />
                            <input type="hidden" id="secretkeyT" name="secretkeyT" />
                            <p id="traceMsg"></p>
                            <p id="traceResult"></p>
                        </td>
                    </tr>
                </table>

            </form>

            <table style="width: 100%; border-collapse: separate; border-spacing: 10px;">
                <tr>
                    <td colspan="4" align="center">
                        <a id="btnSubmit" onclick="submitTraceData();" class="easyui-linkbutton" style="width: 75px; height: 25px">提交</a> &nbsp;&nbsp;&nbsp;&nbsp;
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
        $("#appNameT").combobox({
            //相当于html >> select >> onChange事件
            onChange: function () {
                var appid = $("#appNameT").combobox('getValue');
                $("#appidStrT").html("appid:"+appid.split(",")[0]);
                $("#appkeyStrT").html("appkey:"+appid.split(",")[1]);
                $("#appidT").val(appid.split(",")[0]);
                $("#appkeyT").val(appid.split(",")[1]);
                if (checkNull(appid)) {
                    $("#userInfoT").val('');
                } else {
                    $.post(projectPath + "getUserApiVOList", {
                        "appid": appid.split(",")[0]
                    }, function (data) {
                        if (data.code == '0001') {
                            $('#userInfoT').combobox({
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
        $("#userInfoT").combobox({
            //相当于html >> select >> onChange事件
            onChange: function () {
                var secretkey = $("#userInfoT").combobox('getValue');
                var uid = $('#userInfoT').combobox('getText');
                if (checkNull(secretkey)) {
                    $("#userInfoT").val('');
                } else {
                    $("#uidStrT").html("uid:"+uid);
                    $("#secretkeyStrT").html("secretkey:"+secretkey);
                    $("#uidT").val(uid);
                    $("#secretkeyT").val(secretkey);
                }

            }
        });

        // select选择框触发事件
        $("#projectValueT").combobox({
            //相当于html >> select >> onChange事件
            onChange: function () {
                var projectValue = $("#projectValueT").combobox('getValue');
                var urlType = $("#urlTypeT").val();
                if (checkNull(projectValue)) {
                    $("#projectUrlT").val('');
                } else {
                    $.post(projectPath + "getDemoUrlVOList", {
                        "projectValue": projectValue,
                        "urlType": urlType
                    }, function (data) {
                        if (data.code == '0001') {
                            $("#projectUrlT").textbox('setValue', data.listData[0].projectUrl);
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
            $('#appNameT').combobox({
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
            $('#ipPortT').combobox({
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
            $('#projectValueT').combobox({
                data : data.listData,
                valueField:'projectValue',
                textField:'projectValue'
            });
        },'json');
    }

    // 提交接口信息
    function submitTraceData() {
        $.messager.confirm('提示', '确定要提交此信息吗？', function(r) {
            if (r) {
                var url = projectPath + "doTraceToChainInfo";
                progressLoad();
                $("#traceForm").form('submit',{
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
                            $.messager.alert('交易信息上链成功！', data.msg, 'success');
                            $("#traceMsg").html(data.msg);
                            $("#traceResult").html(data.listData);
                        } else {
                            $.messager.alert('交易信息上链失败！', data.msg, 'error');
                        }
                        progressClose();
                    }
                });
            }
        });
    }
</script>

