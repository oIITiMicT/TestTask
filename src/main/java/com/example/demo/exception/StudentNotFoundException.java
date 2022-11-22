package com.example.demo.exception;

public class StudentNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Student not found: ";

    private final String field;

    public StudentNotFoundException(String field) {
        this(DEFAULT_MESSAGE + field, field);
    }

    public StudentNotFoundException(String message, String field) {
        super(message);
        this.field = field;
    }

    public StudentNotFoundException(String message, String field, Exception e) {
        super(message, e);
        this.field = field;
    }

    public String getField() {
        return field;
    }

}
