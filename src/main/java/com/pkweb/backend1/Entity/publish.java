package com.pkweb.backend1.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "publish")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class publish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PublishID")
    private int publishId;

    @ManyToOne(fetch = FetchType.EAGER)

    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "Content")
    private String content;

    @Column(name = "AnswerNumber", nullable = false)
    private int answerNumber;

    @Column(name = "AuthorName", nullable = false)
    private String authorName;

    @Column(name = "Views", nullable = false)
    private int views;

    @Column(name = "AnswerLatest", nullable = false)
    private Timestamp answerLatest;

    // Getter and Setter methods
    // ... (similar to the ones you have in the Answer entity)
    public int getPublishId() {
        return publishId;
    }

    public void setPublishId(int publishId) {
        this.publishId = publishId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getAnswerNumber() {
        return answerNumber;
    }

    public void setAnswerNumber(int answerNumber) {
        this.answerNumber = answerNumber;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Timestamp getAnswerLatest() {
        return answerLatest;
    }

    public void setAnswerLatest(Timestamp answerLatest) {
        this.answerLatest = answerLatest;
    }

}
