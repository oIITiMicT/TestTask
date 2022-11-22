package com.example.demo.validation;

import com.example.demo.dto.StudentFormDto;
import com.example.demo.model.Student;

public interface StudentFormValidation {

    void validateNewStudentForm(StudentFormDto studentFormDto);

    Student validateUpdateStudentFormAndBuild(StudentFormDto studentFormDto);
}
