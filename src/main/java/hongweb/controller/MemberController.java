package hongweb.controller;

import hongweb.dto.MemberDto;
import hongweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller //주요응답/요청
@RequestMapping("/member")
public class MemberController {

    @Autowired  //자동 빈(메모리) 생성
    MemberService memberService;

    //1. 로그인 페이징
    @GetMapping("/login")
    public String login(){

        return "/member/login";
    }
    //로그인 처리
    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestParam("mid") String mid , @RequestParam("mpassword") String mpassword){

        return memberService.login(mid,mpassword);
    }


    //2.회원가입 페이징
    @GetMapping("/signup")
    public String signup(){
        return "/member/write";
    }

    //회원가입 처리
    @PostMapping("/signup")
    @ResponseBody
    public boolean save(MemberDto  memberDto){

        //서비스 호출
        boolean result=memberService.signup(memberDto);

        return result;
    }


    //3. 로그아웃 페이징
    @GetMapping("/logout")
    public  String logout(){
        memberService.logout();
   //     return "main";
        return  "redirect:/";         //URL 이동
    }

    //4.회원수정 페이징
    @GetMapping("/update")
    public String update(){
        return "/member/update";
    }


    //회원 수정 처리
    @PutMapping("/update")
    @ResponseBody
    public boolean update(@RequestParam("mname") String mname){
        return memberService.update(mname);
    }

    //5.회원 정보 페이징
     @GetMapping("/info")
    public String info(){
        return "/member/info";
     }

     //6.
    @GetMapping("myroom")
    public String myroom(){
        return "/member/myroom";
    }

    //회원탈퇴 페이징
    @GetMapping("/memberdelete")
    public String memberDelete(){
        return "/member/memberdelete";
    }

    @DeleteMapping("/memberdelete")
    @ResponseBody
    public boolean memberDelete(@RequestParam("mpassword") String mpassword){

              boolean result=  memberService.delete(mpassword);
              return result;
    }
}
