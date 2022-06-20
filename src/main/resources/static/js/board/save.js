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
            alert(data);
        }
    });
}