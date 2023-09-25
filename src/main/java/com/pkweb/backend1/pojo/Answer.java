package com.pkweb.backend1.pojo;

import java.sql.Date;

public class Answer {
    Integer AnswerID;
    Integer UserID;
    Date DateTime;
    String AuthorName;
    String Content;

    Integer PublishID;

    public Answer() {
    }

    public Answer(Integer answerID, Integer userID, Date dateTime, String authorName, String content, Integer publishID) {
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

    public Date getDateTime() {
        return DateTime;
    }

    public void setDateTime(Date dateTime) {
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
