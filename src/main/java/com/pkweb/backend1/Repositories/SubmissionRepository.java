package com.pkweb.backend1.Repositories;


import com.pkweb.backend1.Entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUserId(Long userId);
    List<Submission> findByProblemId(Long problemId);
    //根据userid和problemid查找并返回最新的一个
    Optional<Submission> findTopByUserIdAndProblemIdOrderBySubmittedTimeDesc(Long userId, Long problemId);

}
