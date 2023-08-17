package com.pkweb.backend1.controller.pk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class User1 {

    @RestController
    @RequestMapping("/api/users")
    public static class UserController {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @GetMapping("/ids")
        public List<Map<String, Object>> getUserIds() {
            String sql = "SELECT * FROM web_11.User";
            return jdbcTemplate.queryForList(sql);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(User1.class, args);
    }
}
