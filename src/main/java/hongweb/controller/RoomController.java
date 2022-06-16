package hongweb.controller;

import hongweb.dto.RoomDto;
import hongweb.service.RoomService;
import lombok.Getter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/room") //해당클래스 요청 맵핑 (room)
public class RoomController {

    @Autowired
    private RoomService roomService;

    //1.등록 페이지 이동
    @GetMapping("/write")
    public  String write(){
        return "room/write";    // ->templates -> room -> write.html
    }

    //2.등록 처리
    @PostMapping("/write")
    @ResponseBody              //템플릿이 아닌 객체를 반환시 사용되는 어노테이션
    public  boolean write_save(RoomDto roomDto){
            roomService.room_save(roomDto);
        return true;
    }

    //3.방목록 페이지 이동
    @GetMapping("/list")
    public String list(){
        return "room/list";
    }

    /*4.JSON 사용시
    @GetMapping("/roomlist")
    public void roomlist(HttpServletResponse response) {
        JSONObject jo = new JSONObject();
        JSONArray ja = roomService.room_list();
        jo.put("positions", ja);

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(jo);
        } catch (Exception e) {

        }
    }
*/
     //Map 사용시
    @PostMapping("/roomlist")
    @ResponseBody
    public Map<String, List<Map<String,String>>> roomlsit(@RequestBody Map<String,String> Location){

        return roomService.room_list(Location);
    }


    @GetMapping("/getroom")
    @ResponseBody
    public void getRoom(@RequestParam("rno") int rno,HttpServletResponse response){

        try{

            JSONObject object = roomService.getroom(rno);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(object);

        }catch(Exception e){

        }

    }
    @GetMapping("/myroomlist")
    public void  myroomlist(HttpServletResponse response){

        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().print(roomService.myroomlist());

        }catch (Exception e) {
            System.out.println("정보 불러오기 실패!");
        }

    }

    @DeleteMapping("/delete")
    @ResponseBody
    public boolean delete(@RequestParam("rno") int rno){
        return roomService.delete(rno);
    }

}
/*
    -----------  @RequestMapping( "경로" ) --------------------------
    @GetMapping          : FIND , GET   [ @RequestMapping( "경로" , method=RequestMethod.GET )  ]
    @PostMapping         :  SAVE            [ @RequestMapping( "경로" , method=RequestMethod.POST ) ]
    @PutMapping          : UPDATE         [ @RequestMapping( "경로" , method=RequestMethod.PUT ) ]
    @DeleteMapping       : DELETE      [ @RequestMapping( "경로" , method=RequestMethod.DELETE ) ]

    view(JS) -----> controller  변수 요청 방식
        // 1.  HttpServletRequest request 이용한 방식
            String roomname = request.getParameter("roomname");
            String x = request.getParameter("x");
            String y = request.getParameter("y");
        // 2.  @RequestParam("요청변수) 자료형 변수명
            @RequestParam("roomname") String roomname ,
            @RequestParam("x") String x ,
            @RequestParam("y") String y
        // 3. Mapping 사용시  DTO 로 자동 주입 된다
            // 조건1. : Mapping
            // 조건2 : 요청변수명 과 DTO 필드명 동일하다

    Controller -> view( JS )
        // 1. 해당 클래스가 @RestController  이면 메소드 return 객체
                vs  @Controller 이면 메소드 return 값이 템블릿(html)
       // 2. HttpServletResponse response
                    response.getWriter().print()
      // 3.  @ResponseBody 메소드  return 객체
 */