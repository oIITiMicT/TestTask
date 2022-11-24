package com.example.demo.exception;

public class IllegalRequestParamException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Illegal request param: ";

    private final String field;

    public IllegalRequestParamException(String field) {
        this(DEFAULT_MESSAGE + field, field);
    }

    public IllegalRequestParamException(String message, String field) {
        super(message + ": " + field);
        this.field = field;
    }

    public IllegalRequestParamException(String message, String field, Exception e) {
        super(message, e);
        this.field = field;
    }

    public String getField() {
        return field;
    }
}
