package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToMany;
import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name="teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="age")
    private int age;

    @Column(name="email")
    private String email;

    @Column(name="subject")
    private String subject;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "students_teachers",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    @ToString.Exclude
    @JsonIgnore
    private Set<Student> students;

    public void addStudent(Student student) {
        if (students == null) {
            students = new HashSet<>();
        }
        students.add(student);
    }

    public void deleteStudent(Student student) {
        if (student == null) {
            students = new HashSet<>();
        }
        students.remove(student);
    }
}
