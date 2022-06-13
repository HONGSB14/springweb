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
    @PutMapping("/write")
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
    //4.
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
}
