package com.microservice.users.dtos;

public class ErrorDTO {
    private String description;

    public ErrorDTO(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
