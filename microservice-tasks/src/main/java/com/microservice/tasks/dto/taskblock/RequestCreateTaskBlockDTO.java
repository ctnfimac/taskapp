package com.microservice.tasks.dto.taskblock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateTaskBlockDTO {
    private String name;
    private Integer userId;
}
