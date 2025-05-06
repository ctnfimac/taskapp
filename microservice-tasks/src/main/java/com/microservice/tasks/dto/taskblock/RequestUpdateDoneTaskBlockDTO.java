package com.microservice.tasks.dto.taskblock;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateDoneTaskBlockDTO {
    @NotNull(message = "El id del usuario es requerido")
    private Long userId;
}
