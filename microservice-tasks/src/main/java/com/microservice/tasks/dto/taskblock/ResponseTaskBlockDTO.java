package com.microservice.tasks.dto.taskblock;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseTaskBlockDTO {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
}
