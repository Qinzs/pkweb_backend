package com.pkweb.backend1.dao;

import com.pkweb.backend1.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from users where Email = #{Email}")
    public User findByEmail(String Email);

    @Select("select * from users where UserID = #{UserID}")
    public User findById(Integer UserID);

    @Update("update users set Username = #{Username},Password = #{Password},Email = #{Email}," +
            "RegistrationDate = #{RegistrationDate},LastLoginDate = #{LastLoginDate}, Profile = #{Profile} where UserID = #{UserID}")
    public void updateTargetUser(User user);

    @Select("select Username from users where UserID = #{UserID}")
    public List<String> findNameById(Integer UserID);
}
