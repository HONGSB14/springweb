function login(){
alert("헤이");
let mid=$("#mid").val();
let mpassword=$("#mpassword").val();
    $.ajax({
            url:"/member/login",
             method:"post",
            data:{"mid":mid,"mpassword":mpassword},
            success:function(data){
                console.log(data);
                if(data==true){
                        alert("로그인 성공!");
                        location.href="/";
                }else{
                    alert("로그인 실패!");
                }
            }
    });
}
