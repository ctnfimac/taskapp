package com.microservice.users.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class UserResponseDto {
    private Integer id;
    //private String username;
    private String email;
    //private LocalDateTime createdAt;
}
