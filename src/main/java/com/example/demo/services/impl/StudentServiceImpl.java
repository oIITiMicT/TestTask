package com.example.demo.services.impl;

import com.example.demo.dto.StudentFormDto;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.TeacherNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.StudentRepository;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import com.example.demo.validation.StudentFormValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Optional<Student> findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student addTeacherToStudent(Student student, Teacher teacher) {
        student.addTeacher(teacher);
        saveStudent(student);
        return student;
    }

    @Override
    public Student deleteTeacherFromStudent(Student student, Teacher teacher) {
        student.deleteTeacher(teacher);
        saveStudent(student);
        return student;
    }
}
