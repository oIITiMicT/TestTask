package com.example.demo.exception;

public class TeacherNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Teacher not found: ";

    private final String field;

    public TeacherNotFoundException(String field) {
        this(DEFAULT_MESSAGE + field, field);
    }

    public TeacherNotFoundException(String message, String field) {
        super(message);
        this.field = field;
    }

    public TeacherNotFoundException(String message, String field, Exception e) {
        super(message, e);
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
