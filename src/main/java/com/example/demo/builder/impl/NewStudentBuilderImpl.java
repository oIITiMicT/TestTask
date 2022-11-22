package com.example.demo.builder.impl;

import com.example.demo.builder.NewStudentBuilder;
import com.example.demo.dto.StudentFormDto;
import com.example.demo.model.Student;
import com.example.demo.validation.StudentFormValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewStudentBuilderImpl implements NewStudentBuilder {

    private final StudentFormValidation studentFormValidation;

    @Override
    public Student buildStudent(StudentFormDto studentFormDto) {
        studentFormValidation.validateNewStudentForm(studentFormDto);
        Student student = new Student();
        student.setAge(Integer.parseInt(studentFormDto.getAge()));
        student.setFirstName(studentFormDto.getFirstname());
        student.setLastName(studentFormDto.getLastname());
        student.setEmail(studentFormDto.getEmail());
        student.setDirection(studentFormDto.getDirection());
        return student;
    }
}
