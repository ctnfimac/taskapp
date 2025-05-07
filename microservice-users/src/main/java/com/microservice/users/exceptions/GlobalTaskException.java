package com.microservice.users.exceptions;

import com.microservice.users.enums.APIError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Setter
@Getter
public class GlobalTaskException extends RuntimeException{
    private HttpStatus status;
    private String description;

    public GlobalTaskException(APIError error) {
        this.status = error.getHttpStatus();
        this.description = error.getMessage();
    }
}