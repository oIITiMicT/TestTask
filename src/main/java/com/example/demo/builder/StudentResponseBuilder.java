package com.example.demo.builder;

import com.example.demo.dto.StudentResponseDto;
import com.example.demo.model.Student;

public interface StudentResponseBuilder {

    StudentResponseDto buildStudentResponse(Student student);
}
