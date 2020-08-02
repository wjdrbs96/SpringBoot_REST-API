package com.example.restfulwebservice.user;

import com.example.restfulwebservice.helloworld.Message;
import com.example.restfulwebservice.helloworld.StatusEnum;
import com.example.restfulwebservice.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserTestController {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public ResponseEntity<Message> signin(@RequestBody UserTest userTest) {
        Message message = new Message();

        UserTest user = userMapper.loginCheck(userTest);

        if (user == null) {
            message.setMessage("로그인 실패입니다");
            message.setStatus(StatusEnum.BAD_REQUEST);
            message.setData(user);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.setMessage("로그인 성공입니다");
        message.setStatus(StatusEnum.OK);
        message.setData(user);

        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
