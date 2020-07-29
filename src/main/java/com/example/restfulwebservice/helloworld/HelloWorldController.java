package com.example.restfulwebservice.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/hello-world")
    public String helloWorld() {
        return "Hello World";
    }

    // json 형태로 반환
    @GetMapping(path = "/hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable(value = "name") String lee) {
        return new HelloWorldBean(String.format("Hello World, %s", lee));
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternationalized(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message", null, locale);
    }
}
