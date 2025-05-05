package com.microservice.tasks.dto.task;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskRequestUpdateDone {
    @NotNull(message = "El id del usuario es requerido")
    private Long userId;

    @NotNull(message = "El id del bloque de notas es requerido")
    private Long taskBlockId;
}
