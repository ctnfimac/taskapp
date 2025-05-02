package com.microservice.users.mappers;

import com.microservice.users.dtos.auth.RequestAuthLoginDto;
import com.microservice.users.dtos.auth.RequestAuthRegisterDto;
import com.microservice.users.dtos.auth.ResponseAuthLoginDto;
import com.microservice.users.dtos.auth.ResponseAuthRegisterDto;
import com.microservice.users.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    // para el registro de usuarios
    UserEntity requestAuthRequestDtoToUserEntity(RequestAuthRegisterDto requestAuthRegisterDto);
    ResponseAuthRegisterDto userEntityToResponseAuthRegisterDto(UserEntity userEntity);

    // para el login de usuarios
    UserEntity requestAuthLoginDtoToUserEntity(RequestAuthLoginDto requestAuthLoginDto);
    ResponseAuthLoginDto userEntityToResponseAuthLoginDto(UserEntity userEntity);
}
