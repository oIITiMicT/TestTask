package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;

import java.util.Optional;

public interface StudentService {

    Optional<Student> findStudentById(Long id);

    Student saveStudent(Student student);

    void deleteStudentById(Long id);

    Student addTeacherToStudent(Student student, Teacher teacher);

    Student deleteTeacherFromStudent(Student student, Teacher teacher);
}
