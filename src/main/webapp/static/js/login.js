// 判断时候在Iframe框架内,在则刷新父页面
if (self != top) {
    parent.location.reload(true);
    if (!!(window.attachEvent && !window.opera)) {
        document.execCommand("stop");
    } else {
        window.stop();
    }
}

$(function () {

    // 验证码
    /*$("#captcha").click(function() {
        var $this = $(this);
        var url = $this.data("src") + new Date().getTime();
        $this.attr("src", url);
    });*/
    // 登录
    $('#loginform').form({
        url: basePath + '/login',
        onSubmit : function() {
            progressLoad();
            var isValid = $(this).form('validate');
            if(!isValid){
                progressClose();
            }
            return isValid;
        },
        success:function(result){
            progressClose();
            result = $.parseJSON(result);
            if (result.success) {
                window.location.href = basePath + '/index';
            }else{
                // 刷新验证码
                // $("#captcha")[0].click();
                showMsg(result.msg);
            }
        }
    });
    // 注册
    $("#register").click(function () {
        var url = projectPath +"getRoleTypeId";
        // var html="<option value='' selected='true'>请选择</option>";
        $.get(url,function (data) {
            // $.each(data.roleTypeIds,function (index,value) {
            //     html += "<option value="+value.id+">"+value.description+"</option>"
            // });
            $('#roleIds').combobox({
                data : data.roleTypeIds,
                valueField:'id',
                textField:'description'
            });
            registerWindow();
        },'json');

    });

});
function submitForm(){
    $('#loginform').submit();
}
function clearForm(){
    $('#loginform').form('clear');
}
//回车登录
function enterlogin(){
    if (event.keyCode == 13){
        event.returnValue=false;
        event.cancel = true;
        $('#loginform').submit();
    }
}




// 添加注册信息
function registerWindow() {
    $("#register_Window").show();
    $("#register_Window").window({
        title: "注册信息",
        width: 400,
        height: 480,
        closed: false,
        cache: false,
        maximizable: false,
        collapsible: false,
        minimizable: false,
        //可以调整窗口大小
        resizable: false,
        modal: true
    });
}


// 添加注册用户
function registerUser() {
    // 用户名
    var reg_name = $("#reg_name").textbox('getValue');
    // 密码
    var reg_pwd = $("#reg_pwd").textbox('getValue');
    // 确认密码
    var confirm_pwd = $("#confirm_pwd").textbox('getValue');
    // 手机
    var telephone = $("#telephone").textbox('getValue');
    // 邮箱
    var mail = $("#mail").textbox('getValue');
    // 用户名
    var name = $("#name").textbox('getValue');
    // 性别
    // var sex = $("#sex").val();
    // 用户类别
    var userType = $("#userType").val();
    // 角色
    var roleIds = $("#roleIds").combobox("getValue");
    if (checkNull(reg_name)) {
        $.messager.alert("提示", "请输入登录名！", "error", function () {
            $("input", $("#reg_name").next("span")).focus();
        });
        return;
    }
    if (checkNull(name)) {
        $.messager.alert("提示", "请输入用户名！", "error", function () {
            $("input", $("#name").next("span")).focus();
        });
        return;
    }
    if (checkNull(reg_pwd)) {
        $.messager.alert("提示", "请输入密码！", "error", function () {
            $("input", $("#reg_pwd").next("span")).focus();
        });
        return;
    }else if(reg_pwd.length<6 || reg_pwd.length>20){
        $.messager.alert("提示", "请输入6-12位密码！", "error", function () {
            $("input", $("#reg_pwd").next("span")).focus();
        });
        return;
    }
    if (confirm_pwd != reg_pwd) {
        $.messager.alert("提示", "密码和确认密码不一致！", "error", function () {
            $("input", $("#confirm_pwd").next("span")).focus();
        });
        return;
    }
    if (checkMobile(telephone)) {
        $.messager.alert("提示", "请输入格式正确的手机号码！", "error", function () {
            $("input", $("#telephone").next("span")).focus();
        });
        return;
    }
    if (checkEmail(mail)) {
        $.messager.alert("提示", "请输入格式正确的邮箱！", "error", function () {
            $("input", $("#mail").next("span")).focus();
        });
        return;
    }
    if (checkNull(roleIds)) {
        $.messager.alert("提示", "请选择企业类型！", "error");
        return;
    }
    // if (checkNull(sex)) {
    //     $.messager.alert("提示", "请选择性别！", "error");
    //     return;
    // }
    if (checkNull(userType)) {
        $.messager.alert("提示", "请选择用户类别！", "error");
        return;
    }
    progressLoad();
    var ajaxUrl = projectPath + "doRegister";
    $.post(ajaxUrl, {
        "loginName": reg_name,
        "name":name,
        "password": reg_pwd,
        "phone": telephone,
        "email": mail,
        "sex":"",
        "userType":userType,
        "roleIds":roleIds
    }, function (data) {
        progressClose();
        if (data.success) {
            $.messager.alert('注册成功', data.msg, 'info', function () {
                location.href = projectPath + "/login";
            });
        }else{
            $.messager.alert('注册失败', data.msg, 'error');
        }
        progressClose();
    }, "json");
    return;
}
