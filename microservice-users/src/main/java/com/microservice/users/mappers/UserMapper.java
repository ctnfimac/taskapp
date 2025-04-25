package com.microservice.users.mappers;

import com.microservice.users.dtos.UserRequestCreateDto;
import com.microservice.users.dtos.UserRequestUpdateDto;
import com.microservice.users.dtos.UserResponseDto;
import com.microservice.users.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {
    UserEntity userRequestCreateDtoToUserEntity(UserRequestCreateDto userRequestCreateDto);
    UserEntity userRequestUpdateDtoToUserEntity(UserRequestUpdateDto userRequestUpdateDto);
    UserResponseDto userEntityToUserResponseDto(UserEntity userEntity);
}
