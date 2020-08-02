package com.example.restfulwebservice.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserTest {
    String loginId;
    String password;

    public UserTest(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }
}
