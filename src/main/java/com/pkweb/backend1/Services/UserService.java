package com.pkweb.backend1.Services;

import com.pkweb.backend1.Repositories.UserRepository;
import com.pkweb.backend1.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User registerUser(String username, String password, String email) {
        User existingUser = userRepository.findByUsername(username);
        if (existingUser != null) {
            throw new RuntimeException("该用户名已存在");
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password); // Store the password as-is without encryption
        user.setEmail(email);

        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (!Objects.equals(password, user.getPassword())) {
            //打印出具体密码
            System.out.println("password: " + password);
            System.out.println("user.getPassword(): " + user.getPassword());
            throw new RuntimeException("用户名或密码不正确");
        }
        return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    // You can remove the loginUser method for password verification, as password encryption is removed.

    // public User loginUser(String username, String password) {
    //     User user = userRepository.findByUsername(username);
    //     if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
    //         throw new RuntimeException("用户名或密码不正确");
    //     }
    //     return user;
    // }
}