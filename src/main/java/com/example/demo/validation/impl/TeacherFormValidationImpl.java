package com.example.demo.validation.impl;

import com.example.demo.dto.TeacherFormDto;
import com.example.demo.exception.IllegalFormFieldException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.TeacherNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import com.example.demo.validation.TeacherFormValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class TeacherFormValidationImpl implements TeacherFormValidation {

    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";

    private final StudentService studentService;

    private final TeacherService teacherService;


    @Override
    public void validateNewTeacherForm(TeacherFormDto teacherFormDto) {
        String email = teacherFormDto.getEmail();
        String firstName = teacherFormDto.getFirstname();
        String lastName = teacherFormDto.getLastname();
        String subject = teacherFormDto.getSubject();
        String age = teacherFormDto.getAge();
        if (firstName == null) {
            throw new IllegalFormFieldException("firstname");
        }

        if (lastName == null) {
            throw new IllegalFormFieldException("lastname");
        }

        if (email == null) {
            throw new IllegalFormFieldException("email");
        }

        if (subject == null) {
            throw new IllegalFormFieldException("subject");
        }

        if (age == null) {
            throw new IllegalFormFieldException("age");
        }

        if (!Pattern.compile(EMAIL_PATTERN)
                .matcher(email)
                .matches()) {
            throw new IllegalFormFieldException("illegal email", email);
        }

        if (firstName.length() < 3 || firstName.length() > 40) {
            throw new IllegalFormFieldException("illegal length of firstname(must be 3-40)", firstName);
        }

        if (lastName.length() < 3 || lastName.length() > 40) {
            throw new IllegalFormFieldException("illegal length of lastname(must be 3-40)", lastName);
        }

        if (!age.matches("[0-9.]+")) {
            throw new IllegalFormFieldException("age must be a integer number", age);
        }

        int intAge = Integer.parseInt(age);

        if (intAge < 18 || intAge > 100) {
            throw new IllegalFormFieldException("illegal age(must be 18-100)", age);
        }
    }

    @Override
    public Teacher validateUpdateTeacherFormAndBuild(TeacherFormDto teacherFormDto) {
        String id = teacherFormDto.getId();
        String firstName = teacherFormDto.getFirstname();
        String lastName = teacherFormDto.getLastname();
        String email = teacherFormDto.getEmail();
        String age = teacherFormDto.getAge();
        String subject = teacherFormDto.getSubject();
        List<Long> studentsId = teacherFormDto.getStudentsId();

        if (id == null) {
            throw new IllegalFormFieldException("id");
        }

        if (!id.matches("[0-9.]+")) {
            throw new IllegalFormFieldException("id must be a integer number", age);
        }

        Long intId = Long.parseLong(id);

        Teacher responseTeacher = teacherService.findTeacherById(intId).orElseThrow(() -> new TeacherNotFoundException(id));
        if (firstName != null) {
            if (firstName.length() < 3 || firstName.length() > 40) {
                throw new IllegalFormFieldException("illegal length of firstname(must be 3-40)", firstName);
            }
            responseTeacher.setFirstName(firstName);
        }

        if (lastName != null) {
            if (lastName.length() < 3 || lastName.length() > 40) {
                throw new IllegalFormFieldException("illegal length of lastname(must be 3-40)", lastName);
            }
            responseTeacher.setLastName(lastName);
        }

        if (email != null) {
            if (!Pattern.compile(EMAIL_PATTERN)
                    .matcher(email)
                    .matches()) {
                throw new IllegalFormFieldException("illegal email", email);
            }
            responseTeacher.setEmail(email);
        }

        if (age != null) {
            if (!age.matches("[0-9.]+")) {
                throw new IllegalFormFieldException("age must be a integer number", age);
            }

            int intAge = Integer.parseInt(age);

            if (intAge < 18 || intAge > 100) {
                throw new IllegalFormFieldException("illegal age(must be 18-100)", age);
            }
            responseTeacher.setAge(intAge);
        }

        if (subject != null) {
            responseTeacher.setSubject(subject);
        }

        if (studentsId != null) {
            Set<Student> set = new HashSet<>();
            for (Long nowId : studentsId) {
                Student student = studentService.findStudentById(nowId).orElseThrow(() -> new StudentNotFoundException(Long.toString(nowId)));
                set.add(student);
            }
            responseTeacher.setStudents(set);
        }
        return responseTeacher;
    }
}
