package hongweb.service;

import hongweb.domain.member.MemberEntity;
import hongweb.domain.member.MemberRepository;
import hongweb.dto.LoginDto;
import hongweb.dto.MemberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    HttpServletRequest request; //세션을 사용하기 위한 request 객체 선언


    //로직 /트랜잭션
    //1.로그인 처리 메소드
    public boolean login(String mid, String mpassword){
        //1. sql 없이 자바로 처리
        List<MemberEntity> memberEentityList=memberRepository.findAll();
        for(MemberEntity entity : memberEentityList){
                if(entity.getMid().equals(mid) && entity.getMpassword().equals(mpassword)){

                    LoginDto loginDto = LoginDto.builder()
                            .mno(entity.getMno())
                                    .mid(entity.getMid())
                                            .mname(entity.getMname())
                                                    .build();

                    //세션 객체 호출
                   request.getSession().setAttribute("login",loginDto);
                     return true;
                }
        }
        return false;
    }
    //2. 회원가입 처리 메소드
    public boolean signup(MemberDto memberDto){

        //     dto->entity 바꾸는  첫번째 방법
        //바꾸는 이유 : DTO 는 DB 로 들어갈 수 없음

//        MemberEntity memberEntity= MemberEntity.builder()
//                .mid(memberDto.getMid())
//                .mpassword(memberDto.getMpassword())
//                .mname(memberDto.getMname())
//                .build();


        //    dto->entity 바꾸는  두번째 방법
        MemberEntity memberEntity = memberDto.toEntity();
       // jpa 로 통하여  entity를  db에 저장
        memberRepository.save(memberEntity);
        //저장여부 판단
        if(memberEntity.getMno()<1){
            return false;
        }else{
            return true;
        }
    }


    //3.로그아웃 처리 메소드
    public void logout(){
        request.getSession().setAttribute("login",null);  // 해당세션을 null로 대입
    }

    //4.회원 수정 메소드
    @Transactional
    public boolean update(String mname){
        //세션 내 dto 호출
       LoginDto loginDto= (LoginDto) request.getSession().getAttribute("login");
       if(loginDto==null){  //세션이 없으면
           return false;
       }
       MemberEntity memberEntity= memberRepository.findById(loginDto.getMno()).get();
      memberEntity.setMname(mname);    //해당 엔티티의 필드를 수정 하면 자동으로 db도 수정됌
        return true;
    }

    //5.회원 탈퇴 메소드

    @Transactional
    public boolean delete(String mpassword){

      LoginDto loginDto=  (LoginDto)request.getSession().getAttribute("login");
        MemberEntity memberEntity=memberRepository.findById(loginDto.getMno()).get();
        if(memberEntity.getMpassword().equals(mpassword)){
            memberRepository.delete(memberEntity);
            return true;
        }else{
            return false;
        }
    }

}
