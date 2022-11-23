package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Optional<Student> findStudentById(Long id);

    Student saveStudent(Student student);

    void deleteStudentById(Long id);

    Student addTeacherToStudent(Student student, Teacher teacher);

    Student deleteTeacherFromStudent(Student student, Teacher teacher);

    Page<Student> getPageOfStudents(Long page, Long number);

    List<Student> getSortedListOfStudents(String sortBy);

    List<Student> getListOfStudents();
}
