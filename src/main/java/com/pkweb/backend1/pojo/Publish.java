package com.pkweb.backend1.pojo;

import java.sql.Date;

public class Publish {
    private Integer PublishID;
    private Integer UserID;
    private String Content;
    private Integer AnswerNumber;
    private Integer Views;
    private Date AnswerLatest;

    public Publish() {
    }

    public Publish(Integer publishID, Integer userID, String content, Integer answerNumber, Integer views, Date answerLatest) {
        PublishID = publishID;
        UserID = userID;
        Content = content;
        AnswerNumber = answerNumber;
        Views = views;
        AnswerLatest = answerLatest;
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

    public Date getAnswerLatest() {
        return AnswerLatest;
    }

    public void setAnswerLatest(Date answerLatest) {
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
                '}';
    }
}
