package com.example.demo.services.impl;

import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.repository.TeacherRepository;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    @Override
    public Optional<Teacher> findTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public Teacher addStudentToTeacher(Teacher teacher, Student student) {
        teacher.addStudent(student);
        saveTeacher(teacher);
        return teacher;
    }

    @Override
    public Teacher deleteStudentFromTeacher(Teacher teacher, Student student) {
        teacher.deleteStudent(student);
        saveTeacher(teacher);
        return teacher;
    }
}
