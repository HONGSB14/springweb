package hongweb.service;

import hongweb.domain.room.RoomEntity;
import hongweb.domain.room.RoomImgEntity;
import hongweb.domain.room.RoomImgRepository;
import hongweb.domain.room.RoomRepository;
import hongweb.dto.RoomDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.*;

@Service
public class RoomService {
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    RoomImgRepository roomImgRepository;

    //1. 룸 저장
    @Transactional
    public boolean room_save(RoomDto roomDto){

        //1.dto 를 entity 로 변환 (DTO는 DB에 저장할 수 없기 때문에)
        RoomEntity roomEntity=roomDto.toEntity();

        //2.저장 [ 우선적으로 룸을 DB에 저장 ]
        roomRepository.save(roomEntity);

        //3.입력받은 첨부파일을 저장한다.
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
                    //첨부파일 업로드 처리
                    file.transferTo(new File(filepath));

                    //1.이미지 엔티티 객체 생성
                    RoomImgEntity  roomImgEntity=RoomImgEntity.builder()
                            .rimg(uuidfile)
                            .roomEntity(roomEntity) //방객체를 이미지 클래스에 저장 (1 : M )
                            .build();

                    //2.룸 엔티티에 이미지 엔티티 저장   (양방향)
                    roomImgRepository.save(roomImgEntity);

                    //3.이미지 엔티티를 룸 엔티티 에 추가 (양방향)
                    roomEntity.getRoomImgEntityList().add(roomImgEntity);

                }catch(Exception e){
                    System.out.println("파일 저장 실패!! "+e);
                }

            }
        }

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
            //조건
            if(  Double.parseDouble(  entity.getRlon() ) > qa
                    && Double.parseDouble(  entity.getRlon() ) < pa
                    && Double.parseDouble(  entity.getRlat() )   > ha
                    && Double.parseDouble( entity.getRlat() )   < oa
            ){
                //3.map 객체 생성
                Map<String,String> map= new HashMap<>();
                map.put("rtitle",entity.getRtitle());
                map.put("rlat",entity.getRlat());
                map.put("rlon",entity.getRlon());
                map.put("rno", entity.getRno()+"" );
                map.put("rimg",entity.getRoomImgEntityList().get(0).getRimg());
                //4.리스트 넣기
                mapList.add(map);
            }

        }
        Map<String,List <Map <String,String>>> object = new HashMap<>();
        object.put("positions",mapList);

        return object;
    }


    public JSONObject getroom(int rno){
        //1.해당 룸번호 룸 엔티티 찾기
        Optional<RoomEntity> optionalRoomEntity= roomRepository.findById(rno);
        RoomEntity roomEntity = optionalRoomEntity.get();

        //2.뺴온 룸 엔티티 JSON 객체 변환
        JSONObject object= new JSONObject();
            //2-1 . json에 엔티티 필드값 넣기
        object.put("rtitle",roomEntity.getRtitle());
            //2-2 . jsonarray 객체 생성
        JSONArray jsonArray = new JSONArray();
            //2-3. 룸엔티티의 저장된 이미지들 반복문을 이용한 룸이미지를 jsonarray 에 저장
        for(RoomImgEntity roomImgEntity : roomEntity.getRoomImgEntityList()){

            jsonArray.put(roomImgEntity.getRimg());
        }
        //3. jsoonarry 를 json 객체 포함
        object.put("rimglist", jsonArray);
        System.out.println(object);
        return object;
    }
}
