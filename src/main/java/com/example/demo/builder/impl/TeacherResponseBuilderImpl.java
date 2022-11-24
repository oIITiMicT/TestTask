package com.example.demo.builder.impl;

import com.example.demo.builder.TeacherResponseBuilder;
import com.example.demo.dto.TeacherResponseDto;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;


@Component
public class TeacherResponseBuilderImpl implements TeacherResponseBuilder {

    @Override
    public TeacherResponseDto buildTeacherResponse(Teacher teacher) {
        TeacherResponseDto result = TeacherResponseDto.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .lastName(teacher.getLastName())
                .email(teacher.getEmail())
                .subject(teacher.getSubject())
                .age(teacher.getAge())
                .studentsId(new ArrayList<>())
                .build();
        if (teacher.getStudents() != null) {
            List<Long> studentsId = new ArrayList<>();
            for (Student student : teacher.getStudents()) {
                studentsId.add(student.getId());
            }
            result.setStudentsId(studentsId);
        }
        return result;
    }
}
