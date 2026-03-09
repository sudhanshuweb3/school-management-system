package com.fees.management.exception;

import java.util.List;

public class ApiErrorResponse {

    private int status;
    private String message;
    private List<String> errors;


    public ApiErrorResponse(int status, String message, List<String> errors) {
        this.status = status;
        this.message = message;
        this.errors = errors;
    }


    public ApiErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.errors = List.of(); // empty list
    }


    public ApiErrorResponse(int status, List<String> errors) {
        this.status = status;
        this.message = "Validation failed";
        this.errors = errors;
    }


    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getErrors() {
        return errors;
    }
}
