package com.microservice.users.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
public class UserRequestCreateDto {
    @NotBlank
    @Length(max = 20)
    private String username;

    @NotBlank
    @Length(max = 30)
    private String email;
}
