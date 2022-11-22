package com.example.demo.builder.impl;

import com.example.demo.builder.NewTeacherBuilder;
import com.example.demo.dto.TeacherFormDto;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.validation.StudentFormValidation;
import com.example.demo.validation.TeacherFormValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class NewTeacherBuilderImpl implements NewTeacherBuilder {

    private final TeacherFormValidation teacherFormValidation;

    @Override
    public Teacher buildTeacher(TeacherFormDto teacherFormDto) {
        teacherFormValidation.validateNewTeacherForm(teacherFormDto);
        Teacher teacher = new Teacher();
        teacher.setAge(Integer.parseInt(teacherFormDto.getAge()));
        teacher.setFirstName(teacherFormDto.getFirstname());
        teacher.setLastName(teacherFormDto.getLastname());
        teacher.setEmail(teacherFormDto.getEmail());
        teacher.setSubject(teacherFormDto.getSubject());
        return teacher;
    }
}
