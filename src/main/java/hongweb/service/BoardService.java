package hongweb.service;

import hongweb.domain.board.BoardEntity;
import hongweb.domain.board.BoardRepository;
import hongweb.dto.BoardDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    //JAP :  Repository 호출
    @Autowired
    private BoardRepository boardRepository;

    //1.C
    @Transactional
    public boolean save(BoardDto boardDto){
        //1. 특정 로직

        //2. Dto-> Entity                                                                           // get bno 로 저장여부 판단
        int bno=boardRepository.save( boardDto.getBoardEntity() ).getBno();
        //저장여부 판단
        if(bno>=1){
            return true;
        }else {
            return false;
        }
    }
    //2.R [ 인수 : x  반환 : JSON  ]
    public JSONArray getBoardList(){
        //JSON  리스트 형성
        JSONArray jsonArray = new JSONArray();
        //JPA 로 DB에서 빼오기
        List<BoardEntity> boardList=boardRepository.findAll();
        //모든 엔티티를 json으로 변환
        for(BoardEntity entity : boardList){
            JSONObject object = new JSONObject();
            object.put("bno",entity.getBno());
            object.put("btitle",entity.getBtitle());
            object.put("bdate" , entity.getCreatedate() );
            object.put("bcontent",entity.getBcontent());
            object.put("bview",entity.getBview());
            object.put("blike",entity.getBlike());
            jsonArray.put(object);
        }
        return jsonArray;
    }
    //1-2 보드리스트 개별조회
    public JSONObject getBoard(int bno){
        Optional<BoardEntity> optional = boardRepository.findById(bno);
        BoardEntity entity = optional.get();
        JSONObject object = new JSONObject();
        object.put("bno",entity.getBno());
        object.put("btitle",entity.getBtitle());
        object.put("bcontent",entity.getBcontent());
        object.put("bview",entity.getBview());
        object.put("blike",entity.getBlike());
        object.put("bmodate" , entity.getModifiedate() );
        return object;
    }
    //3.U [인수 : 게시물 번호  , 수정할내용들 -> dto ]
    @Transactional
    public boolean update(BoardDto boardDto){
        Optional<BoardEntity> optionalBoard= boardRepository.findById(boardDto.getBno());

        BoardEntity boardEntity =  optionalBoard.get();
        boardEntity.setBtitle(boardDto.getBtitle());
        boardEntity.setBcontent(boardDto.getBcontent());
        return true;

    }

    //4.D   [인수 삭제할 번호  ]
    @Transactional
    public boolean delete(int bno){
       BoardEntity boardEntity= boardRepository.findById(bno).get();
        boardRepository.delete(boardEntity);
        return true;
    }
}
