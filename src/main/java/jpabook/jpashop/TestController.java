package jpabook.jpashop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1") // 요청 매핑
public class TestController {
    @GetMapping("user/search") // get 매핑
    public Map<String, String> search() {
        Map<String, String> response = new HashMap<>();
        response.put("name", "JashinChan");
        response.put("kick", "DropKick");
        response.put("fist", "RoyalCopenhagen");
        return response;
    }
}