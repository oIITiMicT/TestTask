package com.example.demo.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Builder
@Setter
public class StudentResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String direction;
    private int age;
    private List<Long> teachersId;
}
