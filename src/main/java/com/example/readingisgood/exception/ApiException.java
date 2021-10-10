package com.example.readingisgood.exception;

public class ApiException extends RuntimeException {

    private final String detail;
    private final int statusCode;

    public ApiException(String title, String detail, int statusCode) {
        super(title);
        this.detail = detail;
        this.statusCode = statusCode;
    }

    public String getDetail() {
        return detail;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
