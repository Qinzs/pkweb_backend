package com.pkweb.backend1.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "match")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MatchID")
    private Long matchId;

    @Column(name = "User1ID")
    private Long user1Id;

    @Column(name = "User2ID")
    private Long user2Id;

    @Column(name = "ProblemID")
    private Long problemId;

    @Column(name = "StartTime")
    private LocalDateTime startTime;

    @Column(name = "EndTime")
    private LocalDateTime endTime;

    @Column(name = "WinnerID")
    private Long winnerId;

    // Constructors, Getters, Setters and other necessary methods...
    public Match() {
    }
    // Constructor with all fields, getters, setters and other necessary methods can be added.
    public Match(Long user1Id, Long user2Id, Long problemId, LocalDateTime startTime, LocalDateTime endTime, Long winnerId) {
        this.user1Id = user1Id;
        this.user2Id = user2Id;
        this.problemId = problemId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.winnerId = winnerId;
    }

    public Long getMatchId() {
        return matchId;
    }

    public void setMatchId(Long matchId) {
        this.matchId = matchId;
    }
    public Long getUser1Id() {
        return user1Id;
    }

    public void setUser1Id(Long user1Id) {
        this.user1Id = user1Id;
    }

    public Long getUser2Id() {
        return user2Id;
    }


    public void setUser2Id(Long user2Id) {
        this.user2Id = user2Id;
    }


    public Long getProblemId() {
        return problemId;
    }


    public void setProblemId(Long problemId) {
        this.problemId = problemId;
    }


    public LocalDateTime getStartTime() {
        return startTime;
    }


    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }


    public LocalDateTime getEndTime() {
        return endTime;
    }


    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Long getWinnerId() {
        return winnerId;
    }

    public void setWinnerId(Long winnerId) {
        this.winnerId = winnerId;
    }



}
