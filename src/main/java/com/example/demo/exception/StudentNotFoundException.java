package com.example.demo.exception;

public class StudentNotFoundException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Student not found: ";

    private final Long id;

    public StudentNotFoundException(Long id) {
        this(DEFAULT_MESSAGE + id, id);
    }

    public StudentNotFoundException(String message, Long id) {
        super(message);
        this.id = id;
    }

    public StudentNotFoundException(String message, Long id, Exception e) {
        super(message, e);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

}
