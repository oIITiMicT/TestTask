package com.example.demo.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class TeacherFormDto {
    private String id;
    private String firstname;
    private String lastname;
    private String email;
    private String subject;
    private String age;
    private List<Long> studentsId;
}
