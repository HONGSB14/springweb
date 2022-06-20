board_get();
//특정 게시물 확인
function board_get(){
    $.ajax({
        url:"/board/getBoard",
        success:function(data){
           let html =
                                    '<div>게시물 번호:'+data.bno+'</div>'+
                                     '<div>게시물 제목:'+data.btitle+'</div>'+
                                     '<div>게시물 내용:'+data.bcontent+'</div>'+
                                     '<div>게시물 작성일:'+data.bdate+'</div>'+
                                     '<div>게시물 수정일:'+data.bmodate+'</div>'+
                                     '<div>게시물 조회수:'+data.bview+'</div>'+
                                     '<div>게시물 좋아요 수:'+data.blike+'</div>'+
                                     '<button type="button" onclick="board_delete('+data.bno+')">삭제처리</button>';
                                     $("#boardDiv").html(html);
        }
    });
}
//삭제 처리 메소드
function board_delete(bno){
      $.ajax({
            url:"/board/delete",
            data:{"bno":bno},
            method:"DELETE",
            success:function(data){
            }
        });
}
