package com.example.demo.exception;

public class IllegalFormFieldException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Field cannot be null: ";
    private String field;

    public IllegalFormFieldException(String field) {
        this(DEFAULT_MESSAGE + field, field);
    }

    public IllegalFormFieldException(String message, String field) {
        super(message);
        this.field = field;
    }

    public IllegalFormFieldException(String message, String field, Exception e) {
        super(message, e);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}