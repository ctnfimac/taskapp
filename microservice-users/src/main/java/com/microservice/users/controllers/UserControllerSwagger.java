package com.microservice.users.controllers;

import com.microservice.users.dtos.UserResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

@Tag(name = "API de usuarios", description = "Administración de las distintas funcionalidades de los usuarios")
public interface UserControllerSwagger {

    @Operation(
            summary = "Usuario por id",
            description = "Endpoint para obtener un usuario, si existe, por el valor de su id."
    )
    ResponseEntity<UserResponseDto> getById(@PathVariable("id") Long id);

    @Operation(
            summary = "Obtener bloques de tarea",
            description = "Endpoint para obtener todos los bloques de tareas finalizados de un usuario específico"
    )
    ResponseEntity<?> getTaskBlocks(@PathVariable("userId") Long userId);


    @Operation(
            summary = "Obtener tareas",
            description = "Endpoint para obtener todas las tareas correspondientes a un usuario y un bloque de tareas especifico."
    )
    ResponseEntity<?> getTasks(@PathVariable("blockId") Long blockId,
                                           @PathVariable("userId") Long userId);
}
