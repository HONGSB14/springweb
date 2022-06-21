package hongweb.service;

import hongweb.domain.board.BoardEntity;
import hongweb.domain.board.BoardRepository;
import hongweb.domain.board.CategoryEntity;
import hongweb.domain.board.CategoryRepository;
import hongweb.domain.member.MemberEntity;
import hongweb.domain.member.MemberRepository;
import hongweb.dto.BoardDto;
import hongweb.dto.LoginDto;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class BoardService {
    //JAP :  Repository 호출
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private CategoryRepository categoryRepository;

    //1.C
    @Transactional
    public boolean save(BoardDto boardDto){
        //1. 세션호출
        LoginDto loginDto =  (LoginDto) request.getSession().getAttribute("login");
        if(loginDto != null){  //로그인이 되어있으면
            //2.로그인 된 회원의 엔티티 찾기
            Optional<MemberEntity> optionalMember=memberRepository.findById(loginDto.getMno());
                        //findById(pk키 ) => 반환타입 :  Optional클래스  [NULL 저장]
            if(optionalMember.isPresent()){ //NULL이 아니면 (.isPresent() = null값 판단 클래스 )
                //3. Dto-> Entity
                //만약에 기존에 있는 카테고리이면
                boolean sw = false;
                int cno =  0 ;
                List<CategoryEntity> categoryEntityList=categoryRepository.findAll();
                for(CategoryEntity entity: categoryEntityList){
                    if(entity.getCname().equals(boardDto.getCategory())){
                        sw = true;
                        cno = entity.getCno();
                    }
                }
                CategoryEntity categoryEntity = null;
                if( !sw ){ // 카테고리가 없을경우
                    // 1. 카테고리 생성
                    categoryEntity = CategoryEntity.builder()
                            .cname( boardDto.getCategory())
                            .build();
                    categoryRepository.save( categoryEntity );
                }else{ // 카테고리 있을경우
                    categoryEntity = categoryRepository.findById( cno ).get();
                }

                BoardEntity boardEntity =  boardRepository.save( boardDto.getBoardEntity() );
                // 4. 작성자 추가
                boardEntity.setMemberEntity( optionalMember.get() );

                // 카테고리 엔티티 에 게시물 연결
                categoryEntity.getBoardEntityList().add(  boardEntity );
                // 회원엔티티에 게시물 연결
                optionalMember.get().getBoardEntityList().add( boardEntity );

                // 5.반환
                return true;
            }
        }else {
            return false;
        }
        return false;
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
            object.put("bdate" , entity.getCreatedate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) );
            object.put("bcontent",entity.getBcontent());
            object.put("bview",entity.getBview());
            object.put("blike",entity.getBlike());
            object.put("mid",entity.getMemberEntity().getMid());
            jsonArray.put(object);
        }
        return jsonArray;
    }
    //1-2 보드리스트 개별조회
    public JSONObject getBoard(int bno){
        //조회수 증가 처리  [ 기준 ip / 24시간 ]
        String ip=request.getRemoteAddr();  //사용자의 ip 가져오기


        Optional<BoardEntity> optional = boardRepository.findById(bno);
        BoardEntity entity = optional.get();

            //ip 와 보드 bno 합쳐서 세션(서버 내 저장소) 부여 및 호출
            Object com=request.getSession().getAttribute(ip+bno);
            if(com==null){    //만약 세션이 있으면
                System.out.println("세션이있따.");
            }else{      //만약 세션이 없으면
                System.out.println("없따.");
                // ip 와 bno 합쳐서 세션 ( 서버내 저장소 ) 부여
                request.getSession().setAttribute(ip+bno, 1);
                request.getSession().setMaxInactiveInterval(60*60*24);   //세션 허용 시간  [ 초단위  60초(1분) * 60(1*60) *24 ]
                // 조회수 증가 처리
                entity.setBview(entity.getBview()+1);
            }

        JSONObject object = new JSONObject();
        object.put("bno",entity.getBno());
        object.put("btitle",entity.getBtitle());
        object.put("bcontent",entity.getBcontent());
        object.put("bview",entity.getBview());
        object.put("blike",entity.getBlike());
        object.put("bmodate" , entity.getModifiedate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) );
        object.put("mid",entity.getMemberEntity().getMid());
        return object;
    }

    //카테고리 호출 메소드
    public JSONArray getCategoryList(){
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        JSONArray jsonArray = new JSONArray();
        for(CategoryEntity entity : categoryEntityList){
            JSONObject object = new JSONObject();
            object.put("cno",entity.getCno());
            object.put("cname",entity.getCname());
            jsonArray.put(object);
        }
        return jsonArray;
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
