
getnews();

function getnews() {

    $.ajax({
        url:"/getnews",
        success: function(data){
        let html="";
            for(let i=0; i<json.length; i++){
                       '<div class-="newsbox row">'+
                                           ' <div class="col-md-5 m-3">'+
                                                '<div class="row">'+
                                                        '<div class="col-md-4">'+
                                                                '<a href="'+data[i].링크+'">'+
                                                                '<img src="'+data[i].사진+'">'+
                                                        '</div>'+
                                                        '<div class="col-md-8">'+
                                                           ' <span>'+data[i].내용+'</span>'+
                                                      '  </div>'+
                                              '  </div>'+
                                            '</div>'+
                                    '</div>'
              $("#newsbox").html(html);
            }
        }
    });
}