package com.pkweb.backend1.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submission")
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SubmissionID")
    private Long submissionId;

    @Column(name = "UserID")
    private Long userId;

    @Column(name = "ProblemID")
    private Long problemId;

    @Column(name = "SubmittedCode", columnDefinition = "TEXT") // Assuming the code can be long
    private String submittedCode;

    @Column(name = "SubmittedTime")
    private LocalDateTime submittedTime;

    @Column(name = "Result")
    private String result;

    @Column(name = "ExecutionTime")
    private Double executionTime;  // Assuming execution time might be in milliseconds or seconds with decimals

    @Column(name = "MemoryUse")
    private Integer memoryUse;  // Assuming memory use is in KB or MB

    // Constructors, Getters, Setters and other necessary methods...

    public Submission() {
    }

    // Constructor with all fields, getters, setters and other necessary methods can be added.
    public Submission(Long userId, Long problemId, String submittedCode, LocalDateTime submittedTime, String result, Double executionTime, Integer memoryUse) {
        this.userId = userId;
        this.problemId = problemId;
        this.submittedCode = submittedCode;
        this.submittedTime = submittedTime;
        this.result = result;
        this.executionTime = executionTime;
        this.memoryUse = memoryUse;
    }

    public Long getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Long submissionId) {
        this.submissionId = submissionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProblemId() {
        return problemId;
    }

    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }

    public String getSubmittedCode() {
        return submittedCode;
    }

    public void setSubmittedCode(String submittedCode) {
        this.submittedCode = submittedCode;
    }

    public LocalDateTime getSubmittedTime() {
        return submittedTime;
    }

    public void setSubmittedTime(LocalDateTime submittedTime) {
        this.submittedTime = submittedTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Double getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Double executionTime) {
        this.executionTime = executionTime;
    }

    public Integer getMemoryUse() {
        return memoryUse;
    }

    public void setMemoryUse(Integer memoryUse) {
        this.memoryUse = memoryUse;
    }


}
