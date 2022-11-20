package com.example.demo.builder.impl;

import com.example.demo.builder.NewStudentBuilder;
import com.example.demo.dto.StudentFormDto;
import com.example.demo.model.Student;
import com.example.demo.validation.FormsValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NewStudentBuilderImpl implements NewStudentBuilder {

    private final FormsValidation formsValidation;

    @Override
    public Student buildStudent(StudentFormDto studentFormDto) {
        formsValidation.validateNewStudentForm(studentFormDto);
        return Student.builder()
                .firstName(studentFormDto.getFirstname())
                .lastName(studentFormDto.getLastname())
                .direction(studentFormDto.getDirection())
                .email(studentFormDto.getEmail())
                .age(studentFormDto.getAge())
                .build();
    }
}
