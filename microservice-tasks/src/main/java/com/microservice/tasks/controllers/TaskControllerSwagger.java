package com.microservice.tasks.controllers;

import com.microservice.tasks.dto.task.TaskAllResponseDTO;
import com.microservice.tasks.dto.task.TaskRequestUpdateDone;
import com.microservice.tasks.dto.task.TaskResponseDTO;
import com.microservice.tasks.dto.task.TaskResponseUpdateDone;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "API de Tareas", description = "Administración de las funcionalidades de tareas")
public interface TaskControllerSwagger {

    @Operation(
            summary = "Cambiar valor de Done",
            description = "Endpoint para modificar el valor del campo done de una tarea. Si tiene valor false pasa a true y viceversa"
    )
    ResponseEntity<TaskResponseUpdateDone> toogleDoneTask(@PathVariable Long taskId,
                                                                 @RequestBody @Valid TaskRequestUpdateDone taskRequestUpdateDone);


    @Operation(
            summary = "Tareas de un usuario y Bloque de tareas finalizado",
            description = "Endpoint para obtener todas las tareas de un usuario y de un bloque específico."
    )
    ResponseEntity<List<TaskResponseDTO>> getTaskByUserAndBlockFinished(@PathVariable Long blockId, @PathVariable Long userId);

    @Operation(
            summary = "Tareas del bloque de un usuario ",
            description = "Endpoint para obtener todas las tareas de un usuario y de un bloque que este activo"
    )
    ResponseEntity<TaskAllResponseDTO> getTaskByUserAndBlockActive(@PathVariable Long userId);
}
