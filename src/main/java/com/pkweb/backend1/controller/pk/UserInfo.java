package com.pkweb.backend1.controller.pk;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/pk")
@RestController
public class UserInfo {

    @PostMapping("/user")
    public Map<String, String> getUser(@RequestBody LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();
        if ("123".equals(loginRequest.getAccount()) && "456".equals(loginRequest.getPassword())) {
            response.put("status", "200");
        } else {
            response.put("status", "error");
        }
        return response;
    }
}


