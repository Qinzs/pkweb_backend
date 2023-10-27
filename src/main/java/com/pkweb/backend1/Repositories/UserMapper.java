package com.pkweb.backend1.Repositories;

import com.pkweb.backend1.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from [User] where Email = #{Email}")
    public User findByEmail(String Email);

    @Select("select * from [User] where UserID = #{UserID}")
    public User findById(Integer UserID);

    @Update("update [User] set Password = #{Password} where UserID = #{UserID}")
    public void updatePassword(User user);


    @Select("select Username from [User] where UserID = #{UserID}")
    public List<String> findNameById(Integer UserID);
}
