package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class TeacherResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String subject;
    private int age;
    private List<Long> studentsId;
}
