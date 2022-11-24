package com.example.demo.repository;

import com.example.demo.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    List<Teacher> findTeachersByFirstNameAndLastName(String firstName, String lastName);

    List<Teacher> findTeachersByFirstName(String firstName);

    List<Teacher> findTeachersByLastName(String lastName);
}
