package com.example.demo.validation.impl;

import com.example.demo.dto.StudentFormDto;
import com.example.demo.exception.IllegalFormFieldException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.TeacherNotFoundException;
import com.example.demo.model.Student;
import com.example.demo.model.Teacher;
import com.example.demo.services.StudentService;
import com.example.demo.services.TeacherService;
import com.example.demo.validation.StudentFormValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
public class StudentFormValidationImpl implements StudentFormValidation {

    private static final String EMAIL_PATTERN = "^(.+)@(\\S+)$";

    private final StudentService studentService;

    private final TeacherService teacherService;

    @Override
    public void validateNewStudentForm(StudentFormDto studentFormDto) {
        String email = studentFormDto.getEmail();
        String firstName = studentFormDto.getFirstname();
        String lastName = studentFormDto.getLastname();
        String direction = studentFormDto.getDirection();
        String age = studentFormDto.getAge();
        if (firstName == null) {
            throw new IllegalFormFieldException("firstname");
        }

        if (lastName == null) {
            throw new IllegalFormFieldException("lastname");
        }

        if (email == null) {
            throw new IllegalFormFieldException("email");
        }

        if (direction == null) {
            throw new IllegalFormFieldException("direction");
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
    public Student validateUpdateStudentFormAndBuild(StudentFormDto studentFormDto) {
        String id = studentFormDto.getId();
        String firstName = studentFormDto.getFirstname();
        String lastName = studentFormDto.getLastname();
        String email = studentFormDto.getEmail();
        String age = studentFormDto.getAge();
        String direction = studentFormDto.getDirection();
        List<Long> teachersId = studentFormDto.getTeachersId();

        if (id == null) {
            throw new IllegalFormFieldException("id");
        }

        if (!id.matches("[0-9.]+")) {
            throw new IllegalFormFieldException("id must be a integer number", age);
        }

        Long intId = Long.parseLong(id);

        Student responseStudent = studentService.findStudentById(intId).orElseThrow(() -> new StudentNotFoundException(id));
        if (firstName != null) {
            if (firstName.length() < 3 || firstName.length() > 40) {
                throw new IllegalFormFieldException("illegal length of firstname(must be 3-40)", firstName);
            }
            responseStudent.setFirstName(firstName);
        }

        if (lastName != null) {
            if (lastName.length() < 3 || lastName.length() > 40) {
                throw new IllegalFormFieldException("illegal length of lastname(must be 3-40)", lastName);
            }
            responseStudent.setLastName(lastName);
        }

        if (email != null) {
            if (!Pattern.compile(EMAIL_PATTERN)
                    .matcher(email)
                    .matches()) {
                throw new IllegalFormFieldException("illegal email", email);
            }
            responseStudent.setEmail(email);
        }

        if (age != null) {
            if (!age.matches("[0-9.]+")) {
                throw new IllegalFormFieldException("age must be a integer number", age);
            }

            int intAge = Integer.parseInt(age);

            if (intAge < 18 || intAge > 100) {
                throw new IllegalFormFieldException("illegal age(must be 18-100)", age);
            }
            responseStudent.setAge(intAge);
        }

        if (direction != null) {
            responseStudent.setDirection(direction);
        }

        if (teachersId != null) {
            Set<Teacher> set = new HashSet<>();
            for (Long nowId : teachersId) {
                Teacher teacher = teacherService.findTeacherById(nowId).orElseThrow(() -> new TeacherNotFoundException(Long.toString(nowId)));
                set.add(teacher);
            }
            responseStudent.setTeachers(set);
        }
        return responseStudent;
    }
}
