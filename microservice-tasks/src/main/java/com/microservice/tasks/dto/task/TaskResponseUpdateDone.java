package com.microservice.tasks.dto.task;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TaskResponseUpdateDone {
    private String title;
    private Boolean done;
    private Long taskId;
}
