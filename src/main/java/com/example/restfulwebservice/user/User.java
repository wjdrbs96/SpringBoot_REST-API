package com.example.restfulwebservice.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password"})
public class User {
    private Integer id;

    @Size(min = 2, message = "Name is two over")
    private String name;

    @Past
    private Date joinDate;

    private String password;
    private String ssn;
}
