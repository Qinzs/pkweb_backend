package com.pkweb.backend1.Controllers;

import com.pkweb.backend1.Repositories.community.AnswerMapper;
import com.pkweb.backend1.Repositories.community.PublishMapper;
import com.pkweb.backend1.pojo.Answer;
import com.pkweb.backend1.pojo.Publish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import static java.time.LocalTime.now;

@RestController
public class Community {
    @Autowired
    PublishMapper publishMapper;

    @Autowired
    AnswerMapper answerMapper;

    // return all publishes
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
    public String createPublish(@RequestBody Publish publish){
        String authorName = publishMapper.findNameByID(publish.getUserID());
        publish.setAuthorName(authorName);
        setAnswerLatest(publish);
        publishMapper.createPublish(publish);
        System.out.println(publish.toString());
        return "Publish created!";
    }


    /*  add an answer in a Publish object
     *  The url looks like: http://localhost:3000/addAnswer?publishID=1
     *  The input json data looks like:
     *
     *  {
     *   "UserID" : 1,
     *   "Content" : "hi4"
     *  }
     */
    @RequestMapping("/addAnswer")
    public String addAnswer(@RequestParam("publishID") Integer publishID, @RequestBody Answer answer){
        Publish publish = publishMapper.findPublishByID(publishID);
        String authorName = publishMapper.findNameByID(publish.getUserID());
        answer.setAuthorName(authorName);
        publish.setAnswerNumber(publish.getAnswerNumber()+1);
        answer.setPublishID(publish.getPublishID());
        setAnswerDateTime(answer);
        setAnswerLatest(publish);
        answerMapper.createAnswer(answer);
        return "add done!";
    }


    /*  return all questions which in the particular publish object
     *  the url looks like:
     *  http://localhost:3000/communities?publishID=1
     *  the input json data looks like:
     *  {
     *   "UserID" : 1,
     *   "Content" : "h4"
     *  }
     */
    @RequestMapping("/communities")
    public List<Answer> checkAnswers(@RequestParam("publishID") Integer publishID){
        return answerMapper.findAnswerByPublishID(publishID);
    }

    public void setAnswerLatest(@RequestBody Publish publish){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        publish.setAnswerLatest(timestamp);
    }

    public void setAnswerDateTime(@RequestBody Answer answer){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        answer.setDateTime(timestamp);
    }
    // -----------------------Other Operation Methods-------------------------------------------------
    // Don't know how to give front-end
    public void add1View(@RequestBody Publish publish){
        publish.setViews(publish.getViews()+1);
        publishMapper.updatePublish(publish);
    }
}
