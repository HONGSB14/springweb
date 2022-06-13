package hongweb.service;

import hongweb.domain.RoomEntity;
import hongweb.domain.RoomRepository;
import hongweb.dto.RoomDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    //1. 룸 저장
    public boolean room_save(RoomDto roomDto){

        //dto 를 entity 로 변환
        RoomEntity roomEntity = RoomEntity.builder()
                .roomname(roomDto.getRoomname())
                .x(roomDto.getX())
                .y(roomDto.getY())
                .build();
        roomRepository.save(roomEntity);
        return true;
    }

    public JSONArray room_list(){

        //1.모든 entity 호출
        List<RoomEntity> roomEntityList=roomRepository.findAll();

        //2.모든 엔티티-> json 으로 반환
        JSONArray ja=new JSONArray();
        for(RoomEntity roomEntity : roomEntityList){

            JSONObject jo=new JSONObject();
            jo.put("roomname",roomEntity.getRoomname());
            jo.put("lng",roomEntity.getX());
            jo.put("lat",roomEntity.getY());

            ja.put(jo);
        }
        //3.반환
        return ja;
    }
}
