package com.pkweb.backend1.dao.community;

import com.pkweb.backend1.pojo.Answer;
import com.pkweb.backend1.pojo.Publish;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface PublishMapper {
    @Insert("insert into publish(UserID,Content,AnswerNumber,Views,AnswerLatest,AuthorName)" +
            "Values (#{UserID},#{Content},#{AnswerNumber},#{Views},#{AnswerLatest},#{AuthorName}")
    public void createPublish(Publish publish);

    @Select(("SELECT * from publish"))
    public List<Publish> returnAll();

    @Select("SELECT PublishID from publish")
    public List<Integer> PublishIDList();

    @Select("SELECT * from publish where PublishID = #{PublishID}")
    public Publish findPublishByID();

    @Select("SELECT Content from publish where PublishID = #{PublishID}")
    public String findContentByID();

    @Select("SELECT AnswerNumber from publish where PublishID = #{PublishID}")
    public Integer findAnswerNumberByID();

    @Select("SELECT Views from publish where PublishID = #{PublishID}")
    public Integer findViewsByID();

    @Select("SELECT AnswerLatest from publish where PublishID = #{PublishID}")
    public Date findAnswerLastestByID();

    @Update("update publish set UserID=#{UserID},Content=#{Content},AnswerNumber=#{AnswerNumber}," +
            "Views=#{Views},AnswerLatest=#{AnswerLatest},AuthorName=#{AuthorName} where PublishID = #{PublishID}")
    public void updatePublish(Publish publish);

    @Delete("Delete from publish where PublishID = #{PublishID}")
    public void deletePublishByID(Integer PublishID);
}