package com.example.demo.services;

import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import org.springframework.data.domain.Page;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface TeacherService {

    Optional<Teacher> findTeacherById(Long id);

    Teacher saveTeacher(Teacher teacher);

    void deleteTeacherById(Long id);

    Teacher addStudentToTeacher(Teacher teacher, Student student);

    Teacher deleteStudentFromTeacher(Teacher teacher, Student student);

    Page<Teacher> getPageOfTeachers(Long page, Long number);

    List<Teacher> getSortedListOfTeachers(String sortBy);

    List<Teacher> getListOfTeachers();

    Set<Student> getAssociatedStudents(Long teacherId);

    List<Teacher> findTeachersByFirstnameAndLastname(String firstName, String lastName);
}
