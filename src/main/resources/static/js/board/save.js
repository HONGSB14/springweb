// 쓰기 처리 메소드
function board_save(){
let form=$("#saveForm")[0];
console.log(form);
let formData= new FormData(form);
    $.ajax({
        url:"/board/save",
        data:formData,
        method:"POST",
        processData:false,
        contentType:false,
        success:function(data){
           if(data==true){
                alert("게시물 작성 성공");
                location.href="/board/list";
           }else{
                alert("로그인 후 작성이 가능합니다.");
           }
        }
    });
}