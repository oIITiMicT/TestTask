package com.example.demo.builder;

import com.example.demo.dto.TeacherFormDto;
import com.example.demo.model.Teacher;

public interface NewTeacherBuilder {

    Teacher buildTeacher(TeacherFormDto teacherFormDto);
}
