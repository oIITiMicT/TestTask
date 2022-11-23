package com.example.demo.controller;

import com.example.demo.exception.IllegalFormFieldException;
import com.example.demo.exception.IllegalRequestParamException;
import com.example.demo.exception.StudentNotFoundException;
import com.example.demo.exception.TeacherNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<?> studentNotFoundException(StudentNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> illegalFormFieldException(IllegalFormFieldException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> teacherNotFoundException(TeacherNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<?> illegalRequestParamException(IllegalRequestParamException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

}
