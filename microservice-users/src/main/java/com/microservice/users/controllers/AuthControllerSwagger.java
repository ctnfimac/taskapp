package com.microservice.users.controllers;

import com.microservice.users.dtos.auth.RequestAuthLoginDto;
import com.microservice.users.dtos.auth.RequestAuthRegisterDto;
import com.microservice.users.dtos.auth.ResponseAuthRegisterDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(name = "API autenticación de usuarios", description = "Administración del inicio de sesión y registro de los Usuarios")
public interface AuthControllerSwagger {

    @Operation(
            summary = "Inició de Sesión",
            description = "Endpoint para Iniciar Sesión al sistema por parte del usuario."
    )
    ResponseEntity<?> login(@Validated @RequestBody RequestAuthLoginDto requestAuthLoginDto);


    @Operation(
            summary = "Registro de Usuario",
            description = "Endpoint para registrarse en el sistema"
    )
    ResponseEntity<ResponseAuthRegisterDto> register(@Validated @RequestBody RequestAuthRegisterDto requestAuthRegister);

}
