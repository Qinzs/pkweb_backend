package com.pkweb.backend1.Dao;

import com.pkweb.backend1.entity.Answer;
import com.pkweb.backend1.entity.Publish;
import org.apache.ibatis.annotations.*;

import java.sql.Date;

@Mapper
public interface AnswerMapper {

    @Insert("insert into answer(UserID,Content,DateTime,AuthorName)" +
            "Values (#{UserID},#{Content},#{DateTime},#{AuthorName}")
    public void createAnswer(Answer answer);

    @Select("SELECT * from answer where AnswerID = #{AnswerID}")
    public Publish findAnswerByID();

    @Select("SELECT UserID from answer where AnswerID = #{AnswerID}")
    public String findUserIDByID();

    @Select("SELECT AuthorName from answer where AnswerID = #{AnswerID}")
    public String findAuthorNameByID();

    @Select("SELECT Content from answer where AnswerID = #{AnswerID}")
    public String findContentByID();

    @Select("SELECT DateTime from answer where AnswerID = #{AnswerID}")
    public Date findDateTimeByID();

    @Update("update answer set UserID=#{UserID},Content=#{Content},DataTime = #{DateTime}," +
            "AuthorName=#{AuthorName} where AnswerID = #{AnswerID}")
    public void updateAnswer(Answer answer);

    @Delete("Delete from answer where AnswerID = #{AnswerID}")
    public void deleteAnswerByID(Integer AnswerID);
}
