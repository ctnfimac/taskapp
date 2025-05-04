package com.microservice.tasks.exception;

import com.microservice.tasks.enums.APIError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.util.List;

@AllArgsConstructor
@Setter
@Getter
public class GlobalTaskException extends RuntimeException{
    private HttpStatus status;
    private String description;
    //private List<String> reasons;

    public GlobalTaskException(APIError error) {
        this.status = error.getHttpStatus();
        this.description = error.getMessage();
    }
}
