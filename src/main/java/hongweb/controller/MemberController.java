package hongweb.controller;

import hongweb.dto.MemberDto;
import hongweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //주요응답/요청
public class MemberController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @Autowired  //자동 빈(메모리) 생성
    MemberService memberService;

    @GetMapping("/signup")
    @ResponseBody
    public String signup(MemberDto  memberDto){

            //서비스 호출
        memberService.signup(memberDto);

        return "signup";
    }
}
