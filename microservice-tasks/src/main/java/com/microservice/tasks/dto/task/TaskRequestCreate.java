package com.microservice.tasks.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestCreate {
    @NotNull(message = "title es obligatorio")
    private String title;

    @NotNull(message = "userId es obligatorio")
    private Long userId;
}
