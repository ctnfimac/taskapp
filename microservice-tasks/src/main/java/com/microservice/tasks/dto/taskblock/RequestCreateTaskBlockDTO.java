package com.microservice.tasks.dto.taskblock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateTaskBlockDTO {
    private String title;
    private Long userId;
}
