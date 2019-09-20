$(function(){
    $("#form").submit(function(){
        login();
        return false
        });       
});
function login(){
    var user = $("#user").val();
    var pass = $("#pass").val();
    if (user == ""){
        $("#error").text("用户名不能为空");
        $("#user").focus();
        return false;
    }
    if(pass == ""){
        $("#error").text("密码不能为空");
        $("#pass").focus();
        return false;
    }

    $.ajax({
        type: "get",
        url: "http://39.104.135.20/cochain_manager/h5/wlogin",
        data: {'username': $("#user").val(), 'password': $("#pass").val()},
        success: function(data){
            if(data.code==0001){            
                localStorage.setItem("name", data.objData.name);
                localStorage.setItem("address", data.objData.address);
                localStorage.setItem('id',data.objData.id)
                window.open('user.html','_self')
                
            }else {
                $("#error").text(data.msg);
            }
            
        }
    });
}

function getUser(){
 
 
    var name = localStorage.getItem("name");
    var address=localStorage.getItem("address")
    var id=localStorage.getItem("id")
    $('.top h4').text(name)
    $('.top p').text(address)
    $.ajax({
    type: "get",    
    url: "http://39.104.135.20/cochain_manager/h5/token",
    data:{'userId': id},
    success: function(data){        
        if(data.code==0001){
           
            var html=""
            for(var i=0;i<data.listData.length;i++){                         
                html+= '<li class="listdata" id='+i+'>'+
                '<span><a href="score.html">' +data.listData[i].name+ '</a></span>'+
                '<span  class="uid">'+data.listData[i].id+'</span>'+
                '<span>' +data.listData[i].amount+ '</span>'+
                '</li>'
                
            }
            $(".score").html(html)
            $(".listdata").on("click", function(){
                var tokenId=$(this).children('.uid').text()
                localStorage.setItem("tokenId", tokenId); 
                
              });           
                
            
        }else{
            $(".score").html('<li class="no-text">'+data.msg+'</li>')    
        }
    }
    });
    

    
}



