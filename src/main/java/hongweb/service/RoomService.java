package hongweb.service;

import hongweb.domain.RoomEntity;
import hongweb.domain.RoomRepository;
import hongweb.dto.RoomDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.*;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;

    //1. 룸 저장
    public boolean room_save(RoomDto roomDto){

        //dto 를 entity 로 변환
        RoomEntity roomEntity = RoomEntity.builder()
                .rname(roomDto.getRname())
                .x(roomDto.getX())
                .y(roomDto.getY())
                .build();

        //첨부파일
        String uuidfile=null;
        if(roomDto.getRimg().size() !=0){ //첨부파일이 1개 이상이면
            for(MultipartFile file : roomDto.getRimg()){

                //uuid 난수 생성 ( 식별문자 )
                UUID uuid = UUID.randomUUID();
                //2. uuid + 파일명 [ //getOriginalFilename() : 실제 첨부파일 이름 ]
                uuidfile=uuid.toString()+"_"+file.getOriginalFilename().replaceAll("_","_");
                // 경로설정
                String dir="C:\\Users\\504\\IdeaProjects\\springweb\\src\\main\\resources\\static\\upload\\"; //저장경로
                String filepath=dir+uuidfile;
                try{
                    file.transferTo(new File(filepath));
                    roomEntity.setRimg(file.getOriginalFilename());
                }catch(Exception e){
                    System.out.println("파일 저장 실패!! "+e);
                }

            }
        }
        roomRepository.save(roomEntity);
        return true;
    }
    /*
        2.룸 호출
        2-1 JSON
        반환타입 { 키 : [ {} , {} ,{} ] }

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

*/

    // 2-2 MAP
        //{ 키 : 값 } =entry --> map 컬렉션
        //[ 요소1,요소2,요소3 ]--> List  컬렉션
        // list<Map<String,String> >
        //Map<String, list<Map<String,String> >>
    public Map<String,List <Map <String,String>>>  room_list(Map<String,String> Location){

        List<Map<String,String>> mapList = new ArrayList<>();

        //현재 보고 있는 지도 범위
        double qa=Double.parseDouble(Location.get("qa"));
        double pa=Double.parseDouble(Location.get("pa"));
        double ha=Double.parseDouble(Location.get("ha"));
        double oa=Double.parseDouble(Location.get("oa"));

        //1.모든 엔티티 꺼내오기
        List<RoomEntity> roomEntityList =roomRepository.findAll();
        System.out.println(roomEntityList.toString());
        //2. 엔티티-> map
        for(RoomEntity entity : roomEntityList){
            if(  Double.parseDouble(  entity.getY() ) > qa
                    && Double.parseDouble(  entity.getY() ) < pa
                    && Double.parseDouble(  entity.getX() )   > ha
                    && Double.parseDouble(  entity.getX() )   < oa
            ){
                //3.map 객체 생성
                Map<String,String> map= new HashMap<>();
                map.put("roomname",entity.getRname());
                map.put("lng",entity.getX());
                map.put("lat",entity.getY());
                map.put("rno", entity.getRno()+"" );
                map.put("rimg", entity.getRimg() );
                //4.리스트 넣기
                mapList.add(map);
            }

        }
        Map<String,List <Map <String,String>>> object = new HashMap<>();
        object.put("positions",mapList);
        return object;
    }

}
