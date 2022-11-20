package com.example.demo.builder.impl;

import com.example.demo.builder.StudentResponseBuilder;
import com.example.demo.dto.StudentResponseDto;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentResponseBuilderImpl implements StudentResponseBuilder {

    @Override
    public StudentResponseDto buildStudentResponse(Student student) {
        StudentResponseDto result = StudentResponseDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .direction(student.getDirection())
                .age(student.getAge())
                .teachersId(new ArrayList<>())
                .build();
        List<Long> teachersId = new ArrayList<>();
        for (Teacher teacher : student.getTeachers()) {
            teachersId.add(teacher.getId());
        }
        result.setTeachersId(teachersId);
        return result;
    }
}
