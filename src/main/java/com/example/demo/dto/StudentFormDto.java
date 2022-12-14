package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class StudentFormDto {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String direction;
    private String age;
    private List<Long> teachersId;
}
