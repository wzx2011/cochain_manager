
    //设置此处的原因是每次进入界面展示一个随机的验证码，不设置则为空
    window.onload = function (){
        createCode();
    }
    //获取id封装成一个函数$()方便调用
    function $(id){
     //如果传入的参数类型为字符串则获取当前ID元素，否则返回id
    return typeof id==="string"?document.getElementById(id):id;
    }
    //window.onload表示当文档加载完毕时执行函数
    window.onload=function(){
        //获取#notice-tit下面的全部li元素
        var titles=$('notice-tit').getElementsByTagName('li');
        //获取#notice-con下面的全部div元素
        var divs=$('notice-con').getElementsByTagName('div');
        //遍历所有li标签，给每个li加上id和值，并且绑定事件
        for(var i=0;i<titles.length;i++){
            //给每个li加上id和值
            titles[i].id=i;
            //给每个li绑定事件
            titles[i].onmouseover=function(){
            //悬浮后首先应该初始化每个li和div上的类和display
                for(var j=0;j<titles.length;j++){
                    titles[j].className="";
                    divs[j].style.display="none";
                }
            //给当前悬浮元素添加属性
                titles[this.id].className="select";
                divs[this.id].style.display="block";
            }
        }
    }

