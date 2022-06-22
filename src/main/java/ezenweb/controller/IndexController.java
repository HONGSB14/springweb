package ezenweb.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")        // "/" 최상위 경로
    public String index( Model model ){

        return "main"; // HTML 파일명
    }

}