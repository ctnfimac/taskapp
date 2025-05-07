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

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable("id") Long id){
        return userService.getById(id)
                .map( user -> new ResponseEntity<>(userMapper.userEntityToUserResponseDto(user), HttpStatus.OK))
                .orElse( new ResponseEntity<>(null, HttpStatus.NO_CONTENT));
    }

    @GetMapping("{userId}/blocks")
    public ResponseEntity<?> getTaskBlocks(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(userService.getTaskBlockByUser(userId), HttpStatus.OK);
    }

    @GetMapping("{blockId}/{userId}")
    public ResponseEntity<?> getTaskBlocks(@PathVariable("blockId") Long blockId,
                                           @PathVariable("userId") Long userId){
        return new ResponseEntity<>(userService.getTaskByUserAndBlock(blockId,userId), HttpStatus.OK);
    }

}
