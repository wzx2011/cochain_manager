<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户登录</title>
    <meta name="viewport" content="width=device-width">
    <%@ include file="/commons/basejs.jsp" %>
    <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/login.css?v=${version}" />
    <link rel="stylesheet" type="text/css" href="${staticPath }/static/style/css/styles.css?v=${version}" />
    <script type="text/javascript" src="${staticPath }/static/js/login.js?v=${version}" charset="utf-8"></script>
</head>
<body onkeydown="enterlogin();">
<div id="carbonForm">
    <h1>cochainapi管理平台</h1>

    <form method="post" id="loginform">
    <div class="fieldContainer">

            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="formRow">
            <div class="label">
                <label for="name">用户名:</label>
            </div>

            <div class="field">
                <input type="text" name="username" placeholder="请输入登录名"/>
            </div>
        </div>


        <div class="formRow">
            <div class="label">
                <label for="pass">密码:</label>
            </div>

            <div class="field">
                <input id="password" type="password" name="password" placeholder="请输入密码"/>
            </div>
        </div>

    </div> <!-- Closing fieldContainer -->
    </form>
    <span style="text-align: left;color: cadetblue;text-decoration: underline;" id="register">注册账号</span>

    <div class="signupButton">
        <input type="submit" name="submit" id="btnLogin" onclick="submitForm()" value="Signup"/>
    </div>



    <!-- 弹出注册界面 -->
    <div id="register_Window" style="display: none">
        <table id="imgtable" style="width: 100%; margin: auto; border-collapse: separate; border-spacing: 15px;">
            <tr>
                <td align="right" width="30%"><strong style="color:red">*</strong>登录名：</td>
                <td align="left" width="70%">
                    <input class="easyui-textbox" name="reg_name" id="reg_name" style="width:200px;"
                           data-options="required:true,validType:['length[4,20]']"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="30%"><strong style="color:red">*</strong>用户名：</td>
                <td align="left" width="70%">
                    <input class="easyui-textbox" name="name" id="name" style="width:200px;"
                           data-options="required:true,validType:['length[2,20]']"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="30%"><strong style="color:red">*</strong>密码：</td>
                <td align="left" width="70%">
                    <input class="easyui-textbox validatebox" name="reg_pwd" id="reg_pwd" style="width:200px;"
                           type="password" data-options="required:true,validType:['length[6,20]']"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="30%"><strong style="color:red">*</strong>确认密码：</td>
                <td align="left" width="70%">
                    <input class="easyui-textbox validatebox" name="confirm_pwd" id="confirm_pwd" style="width:200px;"
                           type="password" data-options="required:true,validType:['length[6,20]']"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="30%"><strong style="color:red">*</strong>手机：</td>
                <td align="left" width="70%">
                    <input class="easyui-textbox" name="telephone" id="telephone" style="width:200px;"
                           data-options="required:true,validType:['length[11,11]']"/>
                </td>
            </tr>

            <tr>
                <td align="right" width="30%"><strong style="color:red">*</strong>邮箱：</td>
                <td align="left" width="70%">
                    <input class="easyui-textbox" name="mail" id="mail" style="width:200px;"
                           data-options="required:true,validType:['length[0,32]']"/>
                </td>
            </tr>
            <tr>
                <td align="right" width="30%"><strong style="color:red">*</strong>企业类型：</td>
                <td align="left" width="70%">
                    <select  id = "roleIds" name="roleIds" class="easyui-combobox"
                             data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                    </select>
                </td>
            </tr>
            <tr>
                <td align="right" width="30%"><strong style="color:red">*</strong>用户类型：</td>
                <td align="left" width="70%">
                    <select name="userType" id="userType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="0"  selected="selected">管理员</option>
                    </select>
                </td>
            </tr>
            <tr id="addtr">
                <td colspan="2">
                    <div style="text-align: center;">
                        &nbsp;&nbsp;&nbsp;
                        <a onclick="registerUser();" class="easyui-linkbutton" style="width: 75px; height: 25px">保存</a>&nbsp;&nbsp;&nbsp;
                        <a id="btnCancel"  class="easyui-linkbutton" href="/login" style="width: 75px;height: 25px">取消</a>
                        &nbsp;&nbsp;&nbsp;
                    </div>
                </td>
            </tr>
        </table>
    </div>
</div>






</body>
</html>
