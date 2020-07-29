package com.example.restfulwebservice.helloworld;

import lombok.Data;

@Data
public class Message {
    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.FAIL;
        this.data = null;
        this.message = null;
    }
}
