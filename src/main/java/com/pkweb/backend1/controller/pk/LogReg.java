package com.pkweb.backend1.controller.pk;

import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class LogReg {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");
        String email = request.get("email");

//        User user = userService.registerUser(username, password,email);

        return ResponseEntity.ok("user");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String password = request.get("password");

        User user = userService.loginUser(username, password);
        //返还回去一个dto为user的id，username，email，lastlogindate
        // 创建DTO
        UserDTO userDTO = new UserDTO((long) user.getUserId(), user.getUsername(), user.getEmail(), user.getLastLoginDate());
        // 在login方法中：
        userDTO.setId((long) user.getUserId());  // 注意这里是setId
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setLastLoginDate(user.getLastLoginDate());

        return ResponseEntity.ok(userDTO);
    }
    public class UserDTO {
        private Long id;
        private String username;
        private String email;
        private Date lastLoginDate;

        // 构造函数、getters、setters
        public UserDTO(Long id, String username, String email, Date lastLoginDate) {
            this.id = id;
            this.username = username;
            this.email = email;
            this.lastLoginDate = lastLoginDate;
        }


        public Long getId(int userId) {
            return id;
        }
        public String getUsername() {
            return username;
        }
        public String getEmail() {
            return email;
        }

        public Date getLastLoginDate() {
            return lastLoginDate;
        }
        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setLastLoginDate(java.sql.Date lastLoginDate) {
            this.lastLoginDate = lastLoginDate;
        }
    }

}

