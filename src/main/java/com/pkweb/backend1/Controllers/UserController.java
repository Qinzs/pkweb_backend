package com.pkweb.backend1.Controllers;
import com.pkweb.backend1.Entity.answer;
import com.pkweb.backend1.Entity.publish;
import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.Optional;
import org.hibernate.Hibernate;
import java.util.HashMap;
import java.util.Map;

import java.util.List;

@RestController
@RequestMapping("/api1/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    // 获取所有用户
    @GetMapping
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }

    // 根据ID获取用户
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        return userServices.getUserById(id)
                .map(user -> ResponseEntity.ok(user))
                .orElse(ResponseEntity.notFound().build());
    }

    // 创建新用户
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userServices.saveUser(user);
    }

    // 更新用户
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user) {
        if (!userServices.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        user.setUserId(id); // Ensure the ID is set to the path variable ID
        return ResponseEntity.ok(userServices.saveUser(user));
    }

    // 删除用户
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (!userServices.getUserById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        userServices.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // 其他与用户相关的HTTP请求处理方法
    // 写一个会查询user表，获得基本的用户信息（如名字、邮箱、手机号、上次登录时间）。然后查询使用其他表（如answered、discussions等）来获取参与的讨论，回答的问题的数

    @GetMapping("/profile/{id}")
    public ResponseEntity<Map<String, Object>> getUserProfile(@PathVariable Integer id) {
        // 获取基本的用户信息
        Optional<User> userOptional = userServices.getUserById(id);
        if (userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userOptional.get();

        // 获取该用户的答案和发布
        List<answer> userAnswers = userServices.getAnswersByUserId(id);
        List<publish> userPublishes = userServices.getPublishesByUserId(id);

        // 将所有数据组合到一个Map中
        Map<String, Object> userProfile = new HashMap<>();
        userProfile.put("userInfo", user);
        userProfile.put("answers", userAnswers);
        userProfile.put("publishes", userPublishes);
        Hibernate.initialize(userAnswers);
        Hibernate.initialize(userPublishes);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
}

