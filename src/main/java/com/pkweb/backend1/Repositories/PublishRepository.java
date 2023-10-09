package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.answer;
import com.pkweb.backend1.Entity.publish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PublishRepository extends JpaRepository<publish, Integer> {
    // 写一个findByUserId(Integer userId) 方法
    // JpaRepository的第一个参数是实体类型，第二个参数是主键的类型
    // 根据用户ID查找发布
//    java.util.List<publish> findByUserId(Integer userId);
    List<publish> findByUser_UserId(Integer userId);



}
