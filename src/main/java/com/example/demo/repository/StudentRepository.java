package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findStudentsByFirstNameAndLastName(String firstName, String lastName);

    List<Student> findStudentsByFirstName(String firstName);

    List<Student> findStudentsByLastName(String lastName);
}
