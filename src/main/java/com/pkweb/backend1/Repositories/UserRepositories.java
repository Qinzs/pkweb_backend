package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User, Integer> {


    // 根据用户名查找用户
    Optional<User> findByUsername(String username);

}

