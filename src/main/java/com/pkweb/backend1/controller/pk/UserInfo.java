package com.pkweb.backend1.controller.pk;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/pk")
@RestController
public class UserInfo {
    @RequestMapping("/user")
    public Map<String, String> getUser() {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Pankaj Kumar");
        user.put("email", "111");
        user.put("phone", "222");

        return user;
    }
}
