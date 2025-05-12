package com.microservice.users.dtos.auth;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseAuthRegisterDto {
    private String id;
    private String email;
}
