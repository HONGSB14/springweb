package ezenweb.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
//스프링 test 를 위한 MockMvcRequestBuilders 메소드 호출
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc   //MVC 테스트중 C,S,M 까지 가능
    @WebMvcTest // MVC 테스트중 C만 가능
class BoardControllerTest {

    @Autowired
    MockMvc mvc;        // MVC 테스트에 사용되는 클래스

    @Test
    void list() throws Exception {
        //view 가 없다는 가정 하에 HTTP 테스트 할 수 있는 메소드를 제공 = perform
            //mvc.perform( http 요청 메소드( URL ) );

        mvc.perform(get("/board/list")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void view() throws Exception{
        mvc.perform(get("/board/1")).andExpect(status().isOk()).andDo(print());
    }

    @Test
    void update() {
    }

    @Test
    void save() {
    }

    @Test
    void testSave() {
    }

    @Test
    void getboardlist() {
    }

    @Test
    void getboard() {
    }

    @Test
    void testUpdate() {
    }

    @Test
    void delete() {
    }

    @Test
    void getcategorylist() {
    }
}