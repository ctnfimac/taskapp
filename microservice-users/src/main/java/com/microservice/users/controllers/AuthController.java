package com.microservice.users.controllers;

import com.microservice.users.dtos.auth.RequestAuthRegisterDto;
import com.microservice.users.dtos.auth.ResponseAuthRegisterDto;
import com.microservice.users.entities.UserEntity;
import com.microservice.users.mappers.AuthMapper;
import com.microservice.users.services.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@AllArgsConstructor
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private final AuthMapper authMapper;

    @PostMapping("register")
    public ResponseEntity<ResponseAuthRegisterDto> register(@Validated @RequestBody RequestAuthRegisterDto requestAuthRegister){
        UserEntity userEntity = authMapper.requestAuthRequestDtoToUserEntity(requestAuthRegister);
        UserEntity userCreated = authService.register(userEntity);
        ResponseAuthRegisterDto responseAuthRegisterDto = authMapper.userEntityToResponseAuthRegisterDto(userCreated);
        return new ResponseEntity<>(responseAuthRegisterDto, HttpStatus.CREATED);
    }
}
