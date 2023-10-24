package com.pkweb.backend1.Controllers;
import com.pkweb.backend1.Entity.Contact;
import com.pkweb.backend1.Entity.answer;
import com.pkweb.backend1.Entity.publish;
import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.Repositories.ContactRepository;
import com.pkweb.backend1.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.*;

import org.hibernate.Hibernate;

import java.util.stream.Collectors;

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
        //计算userAnswers的长度，然后放进去
        userProfile.put("answersCount", userAnswers.size());  // 获取并存储答案的数量
        userProfile.put("publishesCount", userPublishes.size());  // 获取并存储发布的数量
        // 计算所有publish的views的总和
        int totalViews = userPublishes.stream()
                .mapToInt(publish::getViews)
                .sum();
        userProfile.put("totalViews", totalViews);
        Hibernate.initialize(userAnswers);
        Hibernate.initialize(userPublishes);
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }

    //写一个接口查询用户发布的讨论，回答的问题，最近的比赛
    @GetMapping("/profile/{id}/publishes")
    public ResponseEntity<List<publish>> getUserPublishes(@PathVariable Integer id) {
        List<publish> userPublishes = userServices.getPublishesByUserId(id);
        if (userPublishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userPublishes, HttpStatus.OK);
    }
    //写一个接口查询用户的联系人
    @GetMapping("/{id}/contacts")
    public ResponseEntity<List<ContactDTO>> getUserContacts(@PathVariable Integer id) {
        List<Contact> contacts = userServices.getContactsByUserId(id);

        if (contacts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ContactDTO> contactsInfo = contacts.stream().map(contact -> {
            Integer contactId = contact.getContactId();
            Optional<User> contactUserOptional = userServices.getUserById(contactId);

            ContactDTO contactDTO = new ContactDTO();
            contactDTO.setContactId(contactId);

            if (contactUserOptional.isPresent()) {
                User contactUser = contactUserOptional.get();
                contactDTO.setUsername(contactUser.getUsername().trim()); // 使用trim()方法删除任何额外的空格
                contactDTO.setProfile(contactUser.getProfile());
            }

            return contactDTO;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(contactsInfo, HttpStatus.OK);
    }


    public class ContactDTO {
        private Integer contactId;
        private String username;
        private String profile; // 这里我假设profile是一个String类型，你可以根据需要调整。

        public void setContactId(Integer contactId) {
            this.contactId = contactId;
        }

        public void setUsername(String trim) {
            this.username = trim;
        }

        public void setProfile(byte[] profile) {
            //this.profile = Arrays.toString(profile);
            this.profile = Base64.getEncoder().encodeToString(profile);

        }

        // Getter, Setter, and other methods...
        public Integer getContactId() {
            return contactId;
        }

        public String getUsername() {
            return username;
        }

        public String getProfile() {
            return profile;
        }


    }

//    public class PublishDTO {
//        private Integer publishId;
//        private UserDTO user;  // 注意，这里使用了一个新的UserDTO
//        private String content;
//        private Integer answerNumber;
//        private String authorName;
//        private Integer views;
//        private LocalDateTime answerLatest;
//
//        // getters, setters, etc.
//    }
//    public class UserDTO {
//        private Integer userId;
//        private String username;
//        // ...其他你想要的字段，但不包括profile
//        public UserDTO(User user) {
//            this.userId = user.getUserId();
//            this.username = user.getUsername();
//            // ...其他你想要的字段，但不包括profile
//        }
//        public Integer getUserId() {
//            return userId;
//        }
//
//        public void setUserId(Integer userId) {
//            this.userId = userId;
//        }
//
//        public String getUsername() {
//            return username;
//        }
//
//        public void setUsername(String username) {
//            this.username = username;
//        }
//
//
//        // getters, setters, etc.
//    }

}

