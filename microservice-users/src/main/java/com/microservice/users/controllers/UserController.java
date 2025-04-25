package com.microservice.users.controllers;

import com.microservice.users.dtos.UserRequestCreateDto;
import com.microservice.users.dtos.UserRequestUpdateDto;
import com.microservice.users.dtos.UserResponseDto;
import com.microservice.users.entities.UserEntity;
import com.microservice.users.mappers.UserMapper;
import com.microservice.users.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private final UserMapper userMapper;


    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll(){
        List<UserEntity> listUsers = userService.getAll();
        return new ResponseEntity<>(
                listUsers.stream().map(userMapper::userEntityToUserResponseDto).toList(),
                HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable("id") Long id){
        return userService.getById(id)
                .map( user -> new ResponseEntity<>(userMapper.userEntityToUserResponseDto(user), HttpStatus.OK))
                .orElse( new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Validated @RequestBody UserRequestCreateDto userRequestCreateDto){
        UserEntity userEntity = userMapper.userRequestCreateDtoToUserEntity(userRequestCreateDto);
        UserEntity userEntityCreated = userService.create(userEntity);
        return new ResponseEntity<>(userMapper.userEntityToUserResponseDto(userEntityCreated), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
       return userService.delete(id) ?
               new ResponseEntity<>(HttpStatus.NO_CONTENT) :
               new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable("id") Long id,
                                                  @Validated @RequestBody UserRequestUpdateDto userRequestUpdateDto){
        UserEntity userEntity = userMapper.userRequestUpdateDtoToUserEntity(userRequestUpdateDto);
        UserEntity userUpdated = userService.update(id, userEntity);
        return userUpdated == null ?
                new ResponseEntity<>(HttpStatus.NOT_FOUND):
                new ResponseEntity<>(userMapper.userEntityToUserResponseDto(userUpdated), HttpStatus.OK);

    }
}
