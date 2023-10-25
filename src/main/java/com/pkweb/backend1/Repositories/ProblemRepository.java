package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    // 这里没有需要的方法体，因为JpaRepository为您提供了基础的CRUD操作。

    // 但是，如果您需要添加自定义查询，可以像下面这样：

    // List<Problem> findByDifficultyLevel(String difficultyLevel);
    // 上面的方法会根据难度等级来查找问题。
}
