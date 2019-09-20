require.config({
    paths: {
        echarts: 'http://echarts.baidu.com/build/dist'
    }
});
// 使用
require(
    [
        'echarts',
        'echarts/chart/bar',
        'echarts/chart/pie'
    ],
    drawEcharts
);

// 资产统计
var myPieChart_pie ;
// 应付款统计
var myPieChart_amount ;
// 开票统计
var myPieChart_token ;
// 收支统计
var myBarChart_IncomeAndPay ;
function drawEcharts(ec) {
    myPieChart_pie = ec.init(document.getElementById('pieMain'));
    getPieJson(ec);
    myBarChart_IncomeAndPay = ec.init(document.getElementById('barMain'));
    getIncomePayTokenJspn(ec);
    myPieChart_amount = ec.init(document.getElementById('pieMain2'));
    getAmountJson(ec);
    myPieChart_token = ec.init(document.getElementById('pieMain3'));
    getTokenJson(ec);
}

// 收支统计
function drawBar_IncomeAndPay(ec,result) {
    var income_token = result.income_token /100;
    var pay_token = result.pay_token /-100;
    option = {
        title: {},
        tooltip: {
            trigger: 'default'
        },
        legend: {
            data: ['收入', '支出']
        },
        toolbox: {
            feature: {
                dataView: {show: false, readOnly: false},
                magicType: {show: false, type: ['line', 'bar']},
                restore: {show: false},
                saveAsImage: {show: false}
            }
        },
        calculable: false,
        xAxis: [
            {
                type: 'category',
                data: ['本公司']
            }
        ],
        yAxis: [
            {
                type: 'value'
            }
        ],
        series: [
            {
                name: '收入',
                type: 'bar',
                data: [income_token],
            },
            {
                name: '支出',
                type: 'bar',
                data: [pay_token],
            }
        ]
    };
    myBarChart_IncomeAndPay.setOption(option, true); //当setOption第二个参数为true时，会阻止数据合并
}

// 收支统计
function getIncomePayTokenJspn(ec) {
    // 异步请求数据
    var doIncomeAndPayURL = projectPath + "doIncomeAndPay";
    $.ajax({
        type: "post",
        async: true,
        url: doIncomeAndPayURL,
        data: {},
        dataType: "json",
        success: function (result) {
            drawBar_IncomeAndPay(ec,result);
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myPieChart_pie.hideLoading();
        }
    })
}

// 资产统计
function drawPie(ec,result) {
    var sumTokenAmount =parseInt(result.sumTokenAmount) / 100;
    var uncollected =parseInt(result.uncollected);
    option2 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['圆票余额', '未兑换的账款']
        },
        toolbox: {
            feature: {
                magicType: {
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%'
                        }
                    }
                },
            }
        },
        series: [
            {
                name: '资产统计',
                type: 'pie',
                radius: ['50%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false   //隐藏标示文字
                        },
                        labelLine : {
                            show : false   //隐藏标示线
                        }
                    }
                },
                data: [
                    {value: sumTokenAmount, name: '圆票余额'},
                    {value: uncollected, name: '未兑换的账款'}
                ]
            }
        ]
    };
    myPieChart_pie.setOption(option2, true);

}
// 资产统计
function getPieJson(ec) {
    // 异步请求数据
    var doAssetStatisticsURL = projectPath + "doAssetStatistics";
    $.ajax({
        type: "post",
        async: true,
        url: doAssetStatisticsURL,
        data: {},
        dataType: "json",
        success: function (result) {
            drawPie(ec,result);
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myPieChart_pie.hideLoading();
        }
    })
}

// 应付款统计
function drawRedeemedAmount(ec,result) {
    var redeemedAmount = result.redeemedAmount;
    var notRedeemedAmount = result.notRedeemedAmount;
    option3 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['未兑换的金额', '已兑换了的金额']
        },
        toolbox: {
            feature: {
                magicType: {
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%'
                        }
                    }
                },
            }
        },
        series: [
            {
                name: '应付款项统计',
                type: 'pie',
                radius: ['50%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false   //隐藏标示文字
                        },
                        labelLine : {
                            show : false   //隐藏标示线
                        }
                    }
                },
                data: [
                    {value: notRedeemedAmount, name: '未兑换的金额'},
                    {value: redeemedAmount, name: '已兑换了的金额'}
                ]
            }
        ]
    };
    myPieChart_amount.setOption(option3, true);
}
// 应付款统计
function getAmountJson(ec) {
    // 异步请求数据
    var doRedeemedAmountURL = projectPath + "doRedeemedAmount";
    $.ajax({
        type: "post",
        async: true,
        url: doRedeemedAmountURL,
        data: {},
        dataType: "json",
        success: function (result) {
            drawRedeemedAmount(ec,result);
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myPieChart_amount.hideLoading();
        }
    })
}

// 开票统计
function drawRedeemedToken(ec,result) {
    var redeemedToken = result.redeemedToken / 100;
    var notRedeemedToken = result.notRedeemedToken / 100;
    option4 = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            orient: 'vertical',
            x: 'left',
            data: ['未兑付的Token', '已兑付的Token']
        },
        toolbox: {
            feature: {
                magicType: {
                    type: ['pie', 'funnel'],
                    option: {
                        funnel: {
                            x: '25%',
                            width: '50%'
                        }
                    }
                },
            }
        },
        series: [
            {
                name: '应付款项统计',
                type: 'pie',
                radius: ['50%', '70%'],
                itemStyle : {
                    normal : {
                        label : {
                            show : false   //隐藏标示文字
                        },
                        labelLine : {
                            show : false   //隐藏标示线
                        }
                    }
                },
                data: [
                    {value: notRedeemedToken, name: '未兑付的Token'},
                    {value: redeemedToken, name: '已兑付的Token'}
                ]
            }
        ]
    };
    myPieChart_token.setOption(option4, true);
}
// 开票统计
function getTokenJson(ec) {
    // 异步请求数据
    var doRedeemedTokenURL = projectPath + "doRedeemedToken";
    $.ajax({
        type: "post",
        async: true,
        url: doRedeemedTokenURL,
        data: {},
        dataType: "json",
        success: function (result) {
            if(result.info){
                drawRedeemedToken(ec,result);
            }
        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            myPieChart_token.hideLoading();
        }
    })
}
