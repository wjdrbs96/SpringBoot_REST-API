package com.example.restfulwebservice.helloworld;

import lombok.*;
import springfox.documentation.service.ResponseMessage;

@Data
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Message {
    private String status;
    private String message;
    private Object Body;
}
