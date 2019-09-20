// Get Root Dir
getRootDir = function (js) {
    var path = "";
    var strFullPath = window.document.location.href;
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    var path = prePath + postPath + "/";
    return path;
}

// 项目根目录
var projectPath = getRootDir("common.js");
var imagePath = getRootDir("common.js");
// 手机号
var mobileReg = /^1[3|4|5|7|8]\d{9}$/;
// 邮箱
var emailReg = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
// 邮编
var zipcodeReg = /[1-9]\d{5}(?!\d)/;
// 正整数
var regPositive = /^[1-9]\d*$/;
//整数
var regInteger = /^[0-9]*$/;
// IP地址
var ipReg = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
// 身份证号码为15位或者18位，15位时全为数字，18位前17位为数字，最后一位是校验位，可能为数字或字符X
var iDReg = /^([0-9]{17}[0-9X]{1})|([0-9]{15})$/;
// 英文
var englishReg = /[a-zA-Z]/;
// 中文
var cnReg = /[\u4e00-\u9fa5]/;
// 正数金额
var regAmount = /(^[1-9](\d+)?(\.\d{1,2})?$)|(^0$)|(^\d\.\d{1,2}$)/ ;

(function ($) {
    $.extend({
        // 替换全部
        replaceAll: function (str, str1, str2) {
            return str.replace(new RegExp(str1, "gm"), str2);
        },
        // 为空检查
        checkNull: function (obj) {
            return !$.trim(obj);
        },
        // 手机号检查
        checkMobile: function (obj) {
            return !(mobileReg.test(obj));
        },
        // 邮箱检查
        checkEmail: function (obj) {
            return !(emailReg.test(obj));
        },
        // 邮编检查
        checkZipcode: function (obj) {
            return !(zipcodeReg.test(obj));
        },
        // 正整数检查
        checkPositive: function (obj) {
            return !(regPositive.test(obj));
        },
        // IP地址检查
        checkIp: function (obj) {
            return !(ipReg.test(obj));
        },
        // 身份证检查
        checkID: function (obj) {
            return !(iDReg.test(obj));
        },
        // 英文检查
        checkEn: function (obj) {
            return !(englishReg.test(obj));
        },
        // 中文检查
        checkEn: function (obj) {
            return !(cnReg.test(obj));
        },
        // 正整数金额
        checkAmount: function (obj) {
            return !(regAmount.test(obj));
        }
    });
})(jQuery);

// 日期比较yyyy-MM-dd
function dateCompare(startDate, endDate) {
    if (startDate == '' || endDate == '') {
        return;
    }
    var arr = startDate.split("-");
    var starttime = new Date(arr[0], arr[1], arr[2]);
    var starttimes = starttime.getTime();

    var arrs = endDate.split("-");
    var lktime = new Date(arrs[0], arrs[1], arrs[2]);
    var lktimes = lktime.getTime();

    if (starttimes >= lktimes) {
        return true;
    } else {
        return false;
    }
}

function checkNull(val) {
    if (val == null || $.trim(val).length == 0) {
        return true;
    }
    return false;
}

//手机号检查
function checkMobile(obj) {
    return !(mobileReg.test(obj));
}

// 邮箱检查
function checkEmail(obj) {
    return !(emailReg.test(obj));
}

// 邮编检查
var zipReg = /^[1-9][0-9]{5}$/;

function checkZipcode(obj) {
    return !(zipReg.test(obj));
}

// 正整数检查
function checkPositive(obj) {
    return !(regPositive.test(obj));
}

// 整数检查
function checkInteger(obj) {
    return !(regInteger.test(obj));
}

// IP地址检查
function checkIp(obj) {
    return !(ipReg.test(obj));
}

// 身份证检查
function checkID(obj) {
    return !(iDReg.test(obj));
}

// 英文检查
function checkEn(obj) {
    return !(englishReg.test(obj));
}

// 中文检查
function checkCh(obj) {
    return !(cnReg.test(obj));
}

// 正整数金额
function checkAmount(obj) {
    return !(regAmount.test(obj));
}

// 小数检查
var pointReg = /^\d+\.\d+$/;

function checkPoint(obj) {
    return !(pointReg.test(obj));
}

//验证年月日（yyyy-mm-dd）格式 
function isDate(dateString) {
    var r = dateString.match(/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
    if (r == null) {
        return false;
    }
    var d = new Date(r[1], r[3] - 1, r[4]);
    var num = (d.getFullYear() == r[1] && (d.getMonth() + 1) == r[3] && d.getDate() == r[4]);
    return (num != 0);
}

//格式化图片
function formatPicture(value, row, index) {
    return '<span data-p1=' + index + ' class="easyui-tooltip">' + '<img src="' + imagePath + value + '" style="width:80px; height:60px;" />' + '</span>';
}

//自定义验证规则
$.extend($.fn.validatebox.defaults.rules, {
    zhongwen : {
        validator : function(value) {//value表示在文本框中输入的内容
            if (!checkCh(value)) {
                return true;
            }
        },
        message : "请输入中文格式"
    },
    youbian : {
        validator : function(value) {
            if(!checkZipcode(value)){
                return true;
            }
        },
        message : "邮编错误，（开头不能为0，共6位数字）"
    }
});