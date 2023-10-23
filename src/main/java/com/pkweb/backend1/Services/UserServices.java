package com.pkweb.backend1.Services;

import com.pkweb.backend1.Entity.answer;
import com.pkweb.backend1.Entity.publish;
import com.pkweb.backend1.Entity.User;
import com.pkweb.backend1.Repositories.AnswerRepository;
import com.pkweb.backend1.Repositories.PublishRepository;
import com.pkweb.backend1.Repositories.UserRepositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepositories UserRepositories;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private PublishRepository publishRepository;
    // 获取所有用户
    public List<User> getAllUsers() {
        return UserRepositories.findAll();
    }

    // 根据ID获取用户
    public Optional<User> getUserById(Integer id) {
        return UserRepositories.findById(id);
    }

    // 根据用户名获取用户
    public Optional<User> getUserByUsername(String username) {
        return UserRepositories.findByUsername(username);
    }

    // 创建或更新用户
    public User saveUser(User user) {
        return UserRepositories.save(user);
    }

    // 删除用户
    public void deleteUser(Integer id) {
        UserRepositories.deleteById(id);
    }
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServices.class);

    // 获取与特定用户ID相关的所有答案
    public List<answer> getAnswersByUserId(Integer userId) {
        List<answer> answers = answerRepository.findByUser_UserId(userId);

        LOGGER.info("Fetched answersdaasasds for userId {}: {}", userId, answers);

        return answerRepository.findByUser_UserId(userId);
    }

    // 获取与特定用户ID相关的所有发布
    public List<publish> getPublishesByUserId(Integer userId) {
        return publishRepository.findByUser_UserId(userId);
    }

    public User getUserById(String userId) {
        try {
            Integer intUserId = Integer.parseInt(userId);
            return UserRepositories.findById(intUserId).orElse(null);
        } catch (NumberFormatException e) {
            // userId 字符串不是一个有效的整数
            return null;
        }    }
}

