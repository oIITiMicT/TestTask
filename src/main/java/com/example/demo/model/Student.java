package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
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

@Entity
@Getter
@Setter
@Table(name="student")
public class Student {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name="firstname")
    private String firstName;

    @Column(name="lastname")
    private String lastName;

    @Column(name="age")
    private int age;

    @Column(name="email")
    private String email;

    @Column(name="direction")
    private String direction;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "students_teachers",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    @JsonIgnore
    private Set<Teacher> teachers;

    public void addTeacher(Teacher teacher) {
        if (teachers == null) {
            teachers = new HashSet<>();
        }
        teachers.add(teacher);
    }

    public void deleteTeacher(Teacher teacher) {
        if (teachers == null) {
            teachers = new HashSet<>();
        }
        teachers.remove(teacher);
    }
}
