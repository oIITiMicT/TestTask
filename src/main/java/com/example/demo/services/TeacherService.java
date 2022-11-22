package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

import java.util.Optional;

public interface TeacherService {

    Optional<Teacher> findTeacherById(Long id);

    Teacher saveTeacher(Teacher teacher);

    void deleteTeacherById(Long id);

    Teacher addStudentToTeacher(Teacher teacher, Student student);

    Teacher deleteStudentFromTeacher(Teacher teacher, Student student);
}
