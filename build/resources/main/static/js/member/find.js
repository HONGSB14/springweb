

function idfind(){
    $.ajax({
        url:"/member/idfind",
        data:{"mname" :$("#idmname").val() , "memail" : $("#idmemail").val()},
        success: function(data){
            if(data==""){
                alert("동일한 회원정보가 없습니다.");
            }else{
                $("#findidbox").css("display","block");
            }
        }
    });
}


function pwfind(){
        $.ajax({
            url:"/member/pwfind",
            data:{"mid" :$("#pwmname").val() , "memail" : $("#pwmemail").val()},
            success: function(data){
               if(data == true){
                alert("해당 이메일로 전송");
               }else{
                    alert("동일한 회원정보를 찾을 수 없습니다.");
               }
            }
        });
}