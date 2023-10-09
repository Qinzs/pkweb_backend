package com.pkweb.backend1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;

public class Publish {
    @JsonFormat
    private Integer PublishID;
    @JsonFormat
    private Integer UserID;
    @JsonFormat
    private String Content;
    @JsonFormat
    private Integer AnswerNumber;
    @JsonFormat
    private Integer Views;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp AnswerLatest;

    @JsonFormat
    private String AuthorName;

    public Publish() {
    }

    public Publish(Integer publishID, Integer userID, String content, Integer answerNumber, Integer views, Timestamp answerLatest, String authorName) {
        PublishID = publishID;
        UserID = userID;
        Content = content;
        AnswerNumber = answerNumber;
        Views = views;
        AnswerLatest = answerLatest;
        AuthorName = authorName;
    }

//    public Publish(Integer userID, String content, Integer answerNumber, Integer views, Date answerLatest, String authorName) {
//        UserID = userID;
//        Content = content;
//        AnswerNumber = answerNumber;
//        Views = views;
//        AnswerLatest = answerLatest;
//        AuthorName = authorName;
//    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }

    public Integer getPublishID() {
        return PublishID;
    }

    public void setPublishID(Integer publishID) {
        PublishID = publishID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public Integer getAnswerNumber() {
        return AnswerNumber;
    }

    public void setAnswerNumber(Integer answerNumber) {
        AnswerNumber = answerNumber;
    }

    public Integer getViews() {
        return Views;
    }

    public void setViews(Integer views) {
        Views = views;
    }

    public Timestamp getAnswerLatest() {
        return AnswerLatest;
    }

    public void setAnswerLatest(Timestamp answerLatest) {
        AnswerLatest = answerLatest;
    }

    @Override
    public String toString() {
        return "Publish{" +
                "PublishID=" + PublishID +
                ", UserID=" + UserID +
                ", Content='" + Content + '\'' +
                ", AnswerNumber=" + AnswerNumber +
                ", Views=" + Views +
                ", AnswerLatest=" + AnswerLatest +
                ", AuthorName='" + AuthorName + '\'' +
                '}';
    }
}