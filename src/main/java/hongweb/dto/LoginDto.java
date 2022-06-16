package hongweb.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LoginDto {     //세션에 넣을 dto 생성
    private int mno;
    private String mid;
    private String mname;
}
