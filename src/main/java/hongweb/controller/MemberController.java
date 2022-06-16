package hongweb.controller;

import hongweb.dto.MemberDto;
import hongweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //주요응답/요청
@RequestMapping("/member")
public class MemberController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/signup")
    public String signup(){
        return "/member/write";
    }

    @Autowired  //자동 빈(메모리) 생성
    MemberService memberService;

    @PostMapping("/signup")
    public String save(MemberDto  memberDto){

            //서비스 호출
        memberService.signup(memberDto);

        return "signup";
    }
}
