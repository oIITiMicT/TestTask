package com.example.demo.builder;

import com.example.demo.dto.StudentFormDto;
import com.example.demo.model.Student;

public interface NewStudentBuilder {

    Student buildStudent(StudentFormDto studentFormDto);
}
