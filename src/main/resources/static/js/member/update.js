function updateid(){
 alert("통신");
    $.ajax({
        url:"/member/update",
        method:"PUT",
        data:{"mname":$("#mname").val()},
        success:function(data){
            if(data){
                alert("수정완료");
            }else{
                 alert("수정실패");
            }
        }
    });

}