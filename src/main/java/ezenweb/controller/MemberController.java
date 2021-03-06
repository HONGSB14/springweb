package ezenweb.controller;


import ezenweb.dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller // 템플릿 영역
@RequestMapping("/member")
public class MemberController {

    @Autowired
    MemberService memberService;  // member 서비스 객체 선언

    // 1. 로그인 페이지 이동 매핑
    @GetMapping("/login")
    public String login( ){
        return "/member/login";
    }

    // 시큐리티 사용시에는 시큐리티내 로그인 서비스 사용
//    // 3. 로그인 처리 매핑
//    @PostMapping("/login")
//    @ResponseBody
//    public boolean save(@RequestParam("mid") String mid ,
//                        @RequestParam("mpassword") String mpassword ) {
//        return memberService.login( mid , mpassword );
//    }

    // 시큐리티 사용시에는 시큐리티내 로그아웃 서비스 사용
//    // 4. 로그아웃 처리 매핑
//    @GetMapping("/logout")
//    public String logout( ) {
//        memberService.logout();
//        // return "main";  // 타임리프 반환
//         return "redirect:/"; ///
//    }

    // 5. 회원수정 페이지 이동 매핑
    @GetMapping("/update")
    public String update(){
        return "/member/update";
    }
    // 6. 회원수정 처리 매핑
    @PutMapping("/update")
    @ResponseBody
    public boolean update( @RequestParam("mname") String mname) {

        return memberService.update( mname );
    }
    @GetMapping("/email/{authkey}/{mid}")
    public String signupEmail(@PathVariable("authkey") String authkey ,@PathVariable("mid") String mid){
        //@PathVariable : 경로상 (URL )  변수 요청

        //이메일 검증 처리
        memberService.authsuccess(authkey,mid);
        //화면 전환
        return "member/authsuccess";
    }

    // 7.
    @GetMapping("/info")
    public String info(){
        return "member/info";
    }
    //8.
    @GetMapping("/myroom")
    public String myroom(){
        return "member/myroom";
    }

    //9. 삭제 페이지 이동 매핑
    @GetMapping("/delete")
    public String delete( ){ return  "member/delete"; }
    // 10. 삭제 처리 매핑
    @DeleteMapping("/delete")
    @ResponseBody
    public boolean delete( @RequestParam("mpassword") String mpassword ){
        return memberService.delete( mpassword);
    }

    // 2. 회원가입 페이지 이동 매핑
    @GetMapping("/signup")
    public String signup(){
        return "member/write";
    }

    // 3. 회원가입 처리 매핑
    @PostMapping("/signup")
    @ResponseBody
    public boolean  save( MemberDto memberDto ){
        // 서비스 호출
        boolean result =  memberService.signup( memberDto);
        return result;
    }
    @GetMapping("/find")
    public String find() {
        return "member/find";
    }

    @GetMapping("/idfind")
    @ResponseBody
    public String idfind(@RequestParam("mname") String mname, @RequestParam("memail") String memail) {
        String idfind=memberService.idfind(mname,memail);

        return idfind;
    }
    @GetMapping("/pwdfind")
    public Boolean pwdfind(@RequestParam("mid") String mid, @RequestParam("memail") String memail){
            Boolean result =memberService.pwdfind(mid,memail);

            return result;
    }


}

