package com.microservice.users.connector.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskBlockDTO {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
