package com.microservice.tasks.dto.taskblock;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseCreateTaskBlockDTO {
    private Long id;
    private String title;
    private Long userId;
}
