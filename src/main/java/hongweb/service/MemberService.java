package hongweb.service;

import hongweb.domain.MemberEntity;
import hongweb.domain.MemberRepository;
import hongweb.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    //로직 /트랜잭션
    //1.로그인 처리 메소드
    public boolean login(){
        return false;
    }
    @Autowired
    MemberRepository memberRepository;
    //2. 회원가입 처리 메소드
    public boolean signup(MemberDto memberDto){
        //dto -> entity [ 이유 : DTO 는 DB 로 들어갈 수 없음 ]
        MemberEntity memberEntity= MemberEntity.builder()
                .mid(memberDto.getMid())
                .mpassword(memberDto.getMpassword())
                .mname(memberDto.getMname())
                .build();
        memberRepository.save(memberEntity);
        return false;
    }
}
