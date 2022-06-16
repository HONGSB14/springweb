function signup(){
    alert('버튼작동');

    let form=$("#signupform")[0];
        let formData= new FormData(form);
        console.log(formData);

        $.ajax({
                    url:"/member/signup",
                    method:"POST",
                    data:formData,
                    contentType:false,
                    processData:false,
                    success:function(data){
                    console.log(data);
                        if(data==1){
                                alert('회원가입 성공');
                                location.href="/member/login";
                        }else{
                            alert('회원가입 실패 !!  서비스 오류 : 관리자에게 문의해주십시오.');
                        }
                    }
        });
}

function memberdelete(){
    $.ajax({
        url:"/member/memberdelete",
        method:"delete",
        data:{"mpassword": $("#mpassword").val()},
        success:function(data){
        console.log(data);
            if(data==true){
                    alert("탈퇴성공");
                    location.href="/member/logout";
            }else{
                alert("오류오류");
            }
        }

    });
}