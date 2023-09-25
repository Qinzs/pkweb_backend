package com.pkweb.backend1.controller.pk;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public class CommunityMapper {
    @Autowired
    UserMapper userMapper;



}