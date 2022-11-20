package com.example.demo.validation.impl;

import com.example.demo.dto.StudentFormDto;
import com.example.demo.exception.IllegalFormFieldException;
import com.example.demo.validation.FormsValidation;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class FormValidationImpl implements FormsValidation {

    @Override
    public void validateNewStudentForm(StudentFormDto studentFormDto) {
        String email = studentFormDto.getEmail();
        String firstName = studentFormDto.getFirstname();
        String lastName = studentFormDto.getLastname();
        String direction = studentFormDto.getDirection();
        int age = studentFormDto.getAge();
        String emailPattern = "^(.+)@(\\S+)$";
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

        if (!Pattern.compile(emailPattern)
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

        if (age < 18 || age > 100) {
            throw new IllegalFormFieldException("illegal age(must be 18-100)", Integer.toString(age));
        }
    }
}
