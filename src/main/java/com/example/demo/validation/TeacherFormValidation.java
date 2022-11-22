package com.example.demo.validation;

import com.example.demo.dto.StudentFormDto;
import com.example.demo.dto.TeacherFormDto;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

public interface TeacherFormValidation {

    void validateNewTeacherForm(TeacherFormDto teacherFormDto);

    Teacher validateUpdateTeacherFormAndBuild(TeacherFormDto teacherFormDto);
}
