//package ezenweb.controller;
//
//import ezenweb.dto.MemberDto;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//class MemberControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Test
//    void login() throws Exception {
//        mvc.perform(get("/member/login")).andDo(print());
//    }
//
//    @Test
//    void save() throws  Exception {
//
//        MemberDto memberDto = MemberDto.builder()
//                .mno(1)
//                .mid("sosexy")
//                        .mname("hsb")
//                                .mpassword("123")
//                                        .build();
//
//        mvc.perform(get("/member/save")).andDo(print());
//    }
//
//    @Test
//    void logout() {
//    }
//
//    @Test
//    void update() {
//    }
//
//    @Test
//    void testUpdate() {
//    }
//
//    @Test
//    void info() {
//    }
//
//    @Test
//    void myroom() {
//    }
//
//    @Test
//    void delete() {
//    }
//
//    @Test
//    void testDelete() {
//    }
//
//    @Test
//    void signup() {
//    }
//
//    @Test
//    void testSave() {
//    }
//}