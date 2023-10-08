package com.pkweb.backend1.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;

public class Answer {
    @JsonFormat
    Integer AnswerID;
    @JsonFormat
    Integer UserID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Timestamp DateTime;
    @JsonFormat
    String AuthorName;
    @JsonFormat
    String Content;
    @JsonFormat
    Integer PublishID;

    public Answer() {
    }

    public Answer(Integer answerID, Integer userID, Timestamp dateTime, String authorName, String content, Integer publishID) {
        AnswerID = answerID;
        UserID = userID;
        DateTime = dateTime;
        AuthorName = authorName;
        Content = content;
        PublishID = publishID;
    }

    public Integer getPublishID() {
        return PublishID;
    }

    public void setPublishID(Integer publishID) {
        PublishID = publishID;
    }

    public String getAuthorName() {
        return AuthorName;
    }

    public void setAuthorName(String authorName) {
        AuthorName = authorName;
    }
    public Integer getAnswerID() {
        return AnswerID;
    }

    public void setAnswerID(Integer answerID) {
        AnswerID = answerID;
    }

    public Integer getUserID() {
        return UserID;
    }

    public void setUserID(Integer userID) {
        UserID = userID;
    }

    public Timestamp getDateTime() {
        return DateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        DateTime = dateTime;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "AnswerID=" + AnswerID +
                ", UserID=" + UserID +
                ", DateTime=" + DateTime +
                ", AuthorName='" + AuthorName + '\'' +
                ", Content='" + Content + '\'' +
                ", PublishID=" + PublishID +
                '}';
    }
}
