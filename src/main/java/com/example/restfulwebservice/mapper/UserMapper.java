package com.example.restfulwebservice.mapper;

import com.example.restfulwebservice.user.UserTest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    UserTest loginCheck(UserTest userTest);
}
