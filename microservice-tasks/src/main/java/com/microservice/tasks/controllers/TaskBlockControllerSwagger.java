package com.microservice.tasks.controllers;

import com.microservice.tasks.dto.task.TaskRequestCreate;
import com.microservice.tasks.dto.task.TaskResponseCreate;
import com.microservice.tasks.dto.taskblock.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "API bloque de Tareas", description = "Administración de las funcionalidades de los bloque de tareas")
public interface TaskBlockControllerSwagger {
    @Operation(
            summary = "Crear bloque de tarea",
            description = "Endpoint para dar de alta un nuevo bloque de tarea siempre y cuando no tenga uno sin finalizar"
    )
    ResponseEntity<ResponseCreateTaskBlockDTO> create(@RequestBody @Valid RequestCreateTaskBlockDTO requestCreateTaskBlockDTO);


    @Operation(
            summary = "Crear tarea",
            description = "Endpoint para crear una tarea en un bloque relacionado a un usuario específico"
    )
    ResponseEntity<TaskResponseCreate> createTask(@PathVariable("block_id") Long blockId,
                                                         @RequestBody @Valid TaskRequestCreate taskRequestCreate);


    @Operation(
            summary = "Eliminar tarea",
            description = "Endpoint para eliminar una tarea de un bloque especifico"
    )
    ResponseEntity<Void> deleteTask(@PathVariable("blockId") Long blockId,
                                           @PathVariable("taskId") Long taskId);


    @Operation(
            summary = "Finalizar bloque de tarea",
            description = "Endpoint para finalizar un bloque de tarea siempre y cuando todas las tareas relacionadas al bloque esten con done = true"
    )
    ResponseEntity<ResponseUpdateDoneTaskBlockDTO> finishTaskBlock(@PathVariable Long blockId,
                                                                          @RequestBody RequestUpdateDoneTaskBlockDTO taskBlockDTO);


    @Operation(
            summary = "Obtener bloques de tarea",
            description = "Endpoint para obtener todos los bloques de tarea finalizados de un usuario"
    )
    ResponseEntity<List<ResponseTaskBlockDTO>> getAllTasksBlockByUserId(@PathVariable("userId") Long userId);

    @Operation(
            summary = "Cancelar un bloque de tarea",
            description = "Endpoint para eliminar un bloque de tareas, no finalizado, y también a sus tareas relacionadas"
    )
    ResponseEntity<Void> cancelBlock(@PathVariable("blockId") Long blockId, @PathVariable("userId") Long userId);
}
