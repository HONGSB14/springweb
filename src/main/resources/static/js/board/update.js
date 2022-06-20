
//수정 처리 메소드
function board_update(){
    let form=$("#updateForm")[0];
    console.log(form);
    let formData=new FormData(form);
      $.ajax({
            url:"/board/update",
            data:formData,
            contentType:false,
            processData:false,
            method:"PUT",
            success:function(data){
                alert(data);
            }
        });

}