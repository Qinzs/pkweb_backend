package com.pkweb.backend1.Repositories.community;

import com.pkweb.backend1.pojo.Publish;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;

@Mapper
public interface PublishMapper {
    @Insert("insert into publish(UserID,Content,AnswerNumber,Views,AnswerLatest,AuthorName,Title)" +
            "Values (#{UserID},#{Content},#{AnswerNumber},#{Views},#{AnswerLatest},#{AuthorName},#{Title})")
    public void createPublish(Publish publish);

    @Select(("SELECT * from publish"))
    public List<Publish> returnAll();

    @Select("SELECT PublishID from publish")
    public List<Integer> PublishIDList();

    @Select("SELECT Username from [User] where UserID = #{UserID}")
    public String findNameByID(Integer UserID);

    @Select("SELECT * from publish where PublishID = #{PublishID}")
    public Publish findPublishByID(Integer publishID);

    @Select("SELECT Content from publish where PublishID = #{PublishID}")
    public String findContentByID();

    @Select("SELECT Title from publish where PublishID = #{PublishID}")
    public String findTitleByID();

    @Select("SELECT AnswerNumber from publish where PublishID = #{PublishID}")
    public Integer findAnswerNumberByID();

    @Select("SELECT Views from publish where PublishID = #{PublishID}")
    public Integer findViewsByID();

    @Select("SELECT AnswerLatest from publish where PublishID = #{PublishID}")
    public Date findAnswerLastestByID();

    @Update("update publish set UserID=#{UserID},Title =#{Title},Content=#{Content},AnswerNumber=#{AnswerNumber}," +
            "Views=#{Views},AnswerLatest=#{AnswerLatest},AuthorName=#{AuthorName} where PublishID = #{PublishID}")
    public void updatePublish(Publish publish);

    @Delete("Delete from publish where PublishID = #{PublishID}")
    public void deletePublishByID(Integer PublishID);
}