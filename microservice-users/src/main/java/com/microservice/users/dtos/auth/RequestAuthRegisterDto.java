package com.microservice.users.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class RequestAuthRegisterDto {
    @NotBlank
    @Email(message = "El correo ingresado no es valido")
    @Length(max = 30)
    private String email;

    @NotBlank
    @Length(max = 50)
    private String password;
}
