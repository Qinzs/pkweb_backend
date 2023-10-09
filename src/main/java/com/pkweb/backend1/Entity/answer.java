package com.pkweb.backend1.Entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity
@Table(name = "answer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AnswerID")
    private int answerId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @Column(name = "DateTime", nullable = false)
    private Timestamp dateTime;

    @Column(name = "AuthorName", nullable = false)
    private String authorName;

    @Column(name = "Content", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PublishID", nullable = false)
    private publish publish;

    // Getter and Setter methods
    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public publish getPublish() {
        return publish;
    }

    public void setPublish(publish publish) {
        this.publish = publish;
    }

    // ... 其他的getter和setter方法
}
