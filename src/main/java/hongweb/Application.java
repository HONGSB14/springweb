package hongweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  //스프링 부트의 자동 설정, 스프링 빈 (클래스 메모리) 읽기와 생성을 모두 자동설정
public class Application {

        public static void main(String[] args){
            SpringApplication.run(Application.class);
        }
}
