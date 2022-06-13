package hongweb.controller;

import hongweb.dto.HelloDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 json 으로 반환하는 컨트롤러로  [ 타임리프 반환 X ]
public class HelloController {

    @GetMapping ("/hello")
    public HelloDto hello(){
        //Dto 생성
        HelloDto helloDto = HelloDto.builder()
                .name("유재석")
                .amount(10000)
                .build();

        return helloDto;
    }
}
