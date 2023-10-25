package com.pkweb.backend1.Repositories;


import com.pkweb.backend1.Entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    List<Submission> findByUserId(Long userId);
    List<Submission> findByProblemId(Long problemId);
}
