
$.ajax({
        url:"/room/myroomlist",
        method:'get',
        success: function(data){
            if(data){
    let html="";
                for(let  i =0; i<data.length; i++){
                html +=
                   '<tr>'+
                                            '<td><img src="/upload" width="100%">'+data[i].rtimg+'</td>'+
                                            '<td><span>'+data[i].rtitle+'</span></td>'+
                                            '<td><span>'+data[i].rdate+'</span></td>'+
                                            '<td><button onclick="roomupdate('+data[i].rno+')">수정</button><button onclick="roomdelete('+data[i].rno+)">삭제</button></td>'+
                                        '</tr>';
                }
                $("#myroomtable").html(html);
            }else{

            }
        }
});

function roomdelete(rno){

    $.ajax({
        url:"/room/delete",
        method:"delete",
        data:{"Rno",rno},
        success:function(data){
            if(data==true){
                alert("삭제성공!");
            }else{
                alert("삭제 실패!");
            }
        }
    });
}

function roomupdate(rno){

}