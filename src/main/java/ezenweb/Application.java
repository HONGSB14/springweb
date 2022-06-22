package ezenweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication  //스프링 부트의 자동 설정, 스프링 빈 (클래스 메모리) 읽기와 생성을 모두 자동설정
@EnableJpaAuditing  //jpa 매핑된 엔티티 (테이블) 의 변화 감지 [ 앱 시작시 Entity 감지 기능 ]
public class Application {

        public static void main(String[] args){
            SpringApplication.run(Application.class);
        }
}
