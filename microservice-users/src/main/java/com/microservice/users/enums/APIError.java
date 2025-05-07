package com.microservice.users.enums;

import org.springframework.http.HttpStatus;

public enum APIError {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,"The are attributes with wrong values"),;

    private final HttpStatus httpStatus;
    private final String message;

    APIError(HttpStatus httpStatus, String message){
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
