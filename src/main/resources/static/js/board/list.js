
board_list();
// 2. R 출력 처리 메소드
function board_list(){
        $.ajax({
            url : "/board/getBoardList" ,
            method : "GET",
            success : function( boardlist ){
            console.log(boardlist);

                let html = $("#boardTable").html();

                for( let i = 0 ; i<boardlist.length ; i++ ){
                    html +=
                                              '<tr>'+
                                                      '<td>'+boardlist[i].bno+'</td> '+
                                                      '<td><a href="/board/view/'+boardlist[i].bno+'">'+boardlist[i].btitle+'<a></td> '+
                                                      '<td>'+boardlist[i].bdate+'</td>'+
                                                      '<td>'+boardlist[i].bview+'</td>'+
                                                      '<td>'+boardlist[i].blike+'</td>'+
                                               '</tr>';
                }
                $("#boardTable").html( html );
            }
        });
}
