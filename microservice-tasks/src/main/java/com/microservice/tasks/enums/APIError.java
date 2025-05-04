package com.microservice.tasks.enums;

import org.springframework.http.HttpStatus;

public enum APIError {
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST,"The are attributes with wrong values"),
    BAD_FORMAT(HttpStatus.BAD_REQUEST,"The message not have a correct form"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "User not found"),
    USER_WITH_SAME_ID(HttpStatus.BAD_REQUEST, "There is a user with the same id"),
    EXCEED_NUMBER_OPERATIONS(HttpStatus.TOO_MANY_REQUESTS, "You exceed the number of operations"),
    TASK_BLOCK_ACTIVE(HttpStatus.BAD_REQUEST, "there is an active task block"),;

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
