package com.pkweb.backend1.dao.community;

import com.pkweb.backend1.pojo.Answer;
import com.pkweb.backend1.pojo.Publish;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface AnswerMapper {

    @Insert("insert into answer(UserID,Content,DateTime,AuthorName,PublishID)" +
            "Values (#{UserID},#{Content},#{DateTime},#{AuthorName},#{PublishID})")
    public void createAnswer(Answer answer);

    @Select(("SELECT * from answer"))
    public List<Answer> returnAll();
    @Select("SELECT * from answer where AnswerID = #{AnswerID}")
    public Publish findAnswerByID(Integer AnswerID);

    @Select("SELECT UserID from answer where AnswerID = #{AnswerID}")
    public String findUserIDByID(Integer AnswerID);

    @Select("SELECT AuthorName from answer where AnswerID = #{AnswerID}")
    public String findAuthorNameByID(Integer AnswerID);

    @Select("SELECT Content from answer where AnswerID = #{AnswerID}")
    public String findContentByID();

    @Select("SELECT DateTime from answer where AnswerID = #{AnswerID}")
    public Date findDateTimeByID(Integer AnswerID);

    @Update("update answer set UserID=#{UserID},Content=#{Content},DataTime = #{DateTime}," +
            "AuthorName=#{AuthorName},PublishID = #{PublishID} where AnswerID = #{AnswerID}")
    public void updateAnswer(Answer answer);

    @Delete("Delete from answer where AnswerID = #{AnswerID}")
    public void deleteAnswerByID(Integer AnswerID);

    @Select("SELECT * from answer where PublishID = #{PublishID}")
    public List<Answer> findAnswerByPublishID(Integer PublishID);
}
