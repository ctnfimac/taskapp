package com.microservice.users.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestUpdateDto {
    private String username;
    private String email;
}
