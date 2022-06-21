
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
                                                      '<td>'+boardlist[i].mid+'</td>'+
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
function category_list(){
   $.ajax({
            URL:"/board/save",
            success: function(data){
                    let html ="";
                    for(let i=0; i<data.length; i++){
                        html +=
                          '<button onclick="selectcategory('+data[i]cno+')">'+data[i].cname+'</button>';
                    }
                    $("#categorybox").html( html );
            }
   });
}
function selectcategory(cno){
    alert(cno);
}