package com.microservice.users.dtos.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseAuthLoginDto {
    private Integer id;
    private String email;
}
