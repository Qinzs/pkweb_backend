//package com.pkweb.backend1.controller.pk;
//
//import com.pkweb.backend1.Services.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/users")
//public class LogReg {
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
//        String username = request.get("username");
//        String password = request.get("password");
//        String email = request.get("email");
//
////        User user = userService.registerUser(username, password,email);
//
//        return ResponseEntity.ok("user");
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
//        String username = request.get("username");
//        String password = request.get("password");
//
////        User user = userService.loginUser(username, password);
//
//        return ResponseEntity.ok("user");
//    }
//}
//
