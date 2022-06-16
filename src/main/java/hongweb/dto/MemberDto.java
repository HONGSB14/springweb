package hongweb.dto;

import hongweb.domain.member.MemberEntity;
import lombok.*;

@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class MemberDto {

    private int mno;                          //회원번호
    private String mid;                     //아이디
    private String mpassword;        //패스워드
    private String mname;               //이름

    //Dto -> Entity 로 바꿔주는 메소드
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .mid(this.mid)
                .mpassword(this.mpassword)
                .mname(this.mname)
                .build();
    }

}
