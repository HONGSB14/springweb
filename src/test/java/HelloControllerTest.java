import hongweb.controller.BoardController;
import hongweb.controller.HelloController;
import hongweb.dto.BoardDto;
import org.json.JSONArray;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

//@WebMvcTest( controllers = HelloController.class )
//public class HelloControllerTest {
//
////    @Autowired
////    private BoardController boardController;
////
////    @Autowired
////    private MockMvc mvc; // GET , POST 매핑 API 테스트 사용
////
////    @Test
////    public void hello가_리턴된다() throws Exception  {
////        String hello = "hello";
////        mvc.perform( MockMvcRequestBuilders.get("/hello") )
////                .andExpect(MockMvcResultMatchers.status().isOk() );
////    }
////
////
////    @Test
////    public void boardSaveTest( ){
////
////        BoardDto boardDto = BoardDto.builder()
////                .btitle("안녕")
////                .bcontent("하하하")
////                .build();
////        boolean result =  boardController.save( boardDto );
////        System.out.println(" 저장 여부 확인 : "  + result );
////    }
////    //   @Test
//////    public void boardListTest( ){
//////        JSONArray jsonArray =  boardController.getboardlist();
//////        System.out.println(" 호출 여부 확인 : " + jsonArray );
//////    }
////    @Test
////    public void boardUpdate() {
////        BoardDto boardDto = BoardDto.builder()
////                .bno( 1 )
////                .btitle(" 제목 수정 ")
////                .bcontent(" 내용 수정")
////                .build();
////        boolean result = boardController.update( boardDto );
////        System.out.println("수정 여부 확인 : " + result );
////    }
////    @Test
////    public void boardDelete() {
////        int bno = 1;
////        boolean result = boardController.delete( bno );
////        System.out.println("삭제 여부 확인 : " + result );
////    }
//
//
//}

