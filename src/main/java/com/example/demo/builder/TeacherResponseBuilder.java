package com.example.demo.builder;

import com.example.demo.dto.TeacherResponseDto;
import com.example.demo.model.Teacher;

public interface TeacherResponseBuilder {

    TeacherResponseDto buildTeacherResponse(Teacher teacher);
}
