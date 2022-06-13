package hongweb.dto;

import lombok.*;

@Getter@Setter
@ToString
@NoArgsConstructor  //깡통 생성자
@AllArgsConstructor // 풀 생성자
@Builder // 생성자 사용 규칙 X -> 생성자 만드는데 안정성 보장 [ 인수 순서 , 개수 , null 값 등등 ] 어느정도의 오류를 내가 잡아주겠다.
public class HelloDto {
    private String name;
    private  int amount;


}
