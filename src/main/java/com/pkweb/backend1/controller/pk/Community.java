package com.pkweb.backend1.controller.pk;

import com.pkweb.backend1.dao.community.AnswerMapper;
import com.pkweb.backend1.dao.community.PublishMapper;
import com.pkweb.backend1.pojo.Answer;
import com.pkweb.backend1.pojo.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.List;

@RestController
public class Community {
    @Autowired
    PublishMapper publishMapper;

    @Autowired
    AnswerMapper answerMapper;

    @RequestMapping("/community")
    public List<Publish> returnPublish(){
        return publishMapper.returnAll();
    }

    @RequestMapping("/askQuestions")
    public void newQuestions(Publish publish){
        publishMapper.createPublish(publish);
    }

    @RequestMapping("/addAnswer")
    public void addAnswer(Publish publish, Answer answer){
        publish.setAnswerNumber(publish.getAnswerNumber()+1);
        answer.setPublishID(publish.getPublishID());
        answerMapper.createAnswer(answer);
        setAnswerLatest(publish);
    }

    public void setAnswerLatest(Publish publish){
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date sqlCurrentDate = new java.sql.Date(currentDate.getTime());
        publish.setAnswerLatest(sqlCurrentDate);
    }
    // -----------------------Other Operation Methods-------------------------------------------------
    // Don't know how to give front-end
    public void add1View(Publish publish){
        publish.setViews(publish.getViews()+1);
        publishMapper.updatePublish(publish);
    }

    public List<Answer> returnAllQuestion(Publish publish){
        return answerMapper.findAnswerByPublishID(publish.getPublishID());
    }



}
