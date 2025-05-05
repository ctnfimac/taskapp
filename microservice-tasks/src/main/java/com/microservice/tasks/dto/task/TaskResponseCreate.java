package com.microservice.tasks.dto.task;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskResponseCreate {
    private Integer id;
    private String title;
}
