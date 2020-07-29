package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController { // 왜 컨트롤러에 애노테이션 붙이는 지는 알아야 된다 함.. 공부 필요

    @GetMapping("hello") // 이거는 왜 하는지 알 것 같음, url 라우트임
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }
}
