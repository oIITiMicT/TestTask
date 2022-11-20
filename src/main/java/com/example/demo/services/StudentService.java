package com.example.demo.services;

import com.example.demo.model.Student;

import java.util.Optional;

public interface StudentService {

    Optional<Student> findStudentById(Long id);

    Student saveStudent(Student student);
}
