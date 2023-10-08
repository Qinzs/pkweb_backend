package com.pkweb.backend1.controller.pk;

import com.pkweb.backend1.dao.community.AnswerMapper;
import com.pkweb.backend1.dao.community.PublishMapper;
import com.pkweb.backend1.pojo.Answer;
import com.pkweb.backend1.pojo.Publish;
import com.pkweb.backend1.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import static java.time.LocalTime.now;

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


    /* Add a new publish
    front-end give json data like this:
    {
    "UserID" : 1,
    "Content" : "hi3",
    "AnswerNumber" : 0,
    "Views" : 0
    }
     */
    @RequestMapping ("/askQuestions")
    public String newPublish(@RequestBody Publish publish){
        String authorName = publishMapper.findNameByID(publish.getUserID());
        publish.setAuthorName(authorName);
        setAnswerLatest(publish);
        publishMapper.createPublish(publish);
        System.out.println(publish.toString());
        return "Publish created!";
    }


    @RequestMapping("/addAnswer")
    public void addAnswer(@RequestBody Integer publishID, Answer answer){
        Publish publish = publishMapper.findPublishByID(publishID);
        publish.setAnswerNumber(publish.getAnswerNumber()+1);
        answer.setPublishID(publish.getPublishID());
        answerMapper.createAnswer(answer);
        setAnswerLatest(publish);
    }

    public void setAnswerLatest(@RequestBody Publish publish){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        publish.setAnswerLatest(timestamp);
    }
    // -----------------------Other Operation Methods-------------------------------------------------
    // Don't know how to give front-end
    public void add1View(@RequestBody Publish publish){
        publish.setViews(publish.getViews()+1);
        publishMapper.updatePublish(publish);
    }

    public List<Answer> returnAllQuestion(Publish publish){
        return answerMapper.findAnswerByPublishID(publish.getPublishID());
    }



}
