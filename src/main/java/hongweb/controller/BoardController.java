package hongweb.controller;

import hongweb.dto.BoardDto;
import hongweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private HttpServletRequest request;          // 1. 세션 호출을 위한 request 객체 생성
    @Autowired
    private BoardService boardService;           //2. 서비스 호출을 위한 boardService 객체 생성

    //////////////////////////////////////// 1. view 열기[ 템플릿 연결 ] 매핑 ////////////////////////////////////////////////////////////
        // 1. 게시판 페이지 열기
        @GetMapping("/list")
        public String boardView(){
            return "board/list";
        }

        //1-2.게시판 개별 조회 페이지
        @GetMapping("/view/{bno}")      //URL 경로에 변수= {변수명}
        public String view(@PathVariable("bno") int bno ){
            // 1. 내가 보고 있는 게시물의 번호를 세션 저장
            request.getSession().setAttribute("bno", bno);
            return "board/view";
        }
        //1-3.게시판  수정 페이지
        @GetMapping("/update")
        public String boardUpdate(){
            return "board/update";
        }

        //1-4. 저장 페이지 (쓰기)
        @GetMapping("/save")
        public String boardSave(){
            return "board/save";
        }



        //////////////////////////////////////////////////////////////2.service 처리 매핑/////////////////////////////////////////////////////

        //1.C
        @PostMapping("/save")
        @ResponseBody
        public boolean save(BoardDto boardDto){
            return  boardService.save(boardDto);
        }

        //2.R
        //모든 글 조회
        @GetMapping("/getBoardList")
        public void getBoardList(HttpServletResponse response){
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().println(boardService.getBoardList());
            }catch (Exception e) {
                System.out.println( e );
            }
        }
        //개별 조회
        @GetMapping("/getBoard")
        public void getBoard(HttpServletResponse response){
                int bno =  (Integer) request.getSession().getAttribute("bno");
            try {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json");
                response.getWriter().print(boardService.getBoard(bno));
            }catch (Exception e) {
                System.out.println(e);
            }
        }

        @GetMapping("/getCategoryList")
        public void getCategoryList(HttpServletResponse response){
            try {

            }catch (Exception e) {

            }
        }

        //3.U
        @PutMapping("/update")
        @ResponseBody
        public boolean update(BoardDto boardDto){
            int bno =  (Integer) request.getSession().getAttribute("bno");
            boardDto.setBno( bno );
            return boardService.update(boardDto);
        }

        //4.D
        @DeleteMapping("/delete")
        @ResponseBody
        public boolean delete(@RequestParam("bno") int bno){
            return boardService.delete(bno);
        }
}
