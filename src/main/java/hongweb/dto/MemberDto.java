package hongweb.dto;

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
}
