package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.Entity.answer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AnswerRepository extends JpaRepository<answer, Integer> {
    List<answer> findByUser_UserId(Integer userId);
}
