package com.microservice.users.services;

import com.microservice.users.entities.UserEntity;

public interface AuthService {
    UserEntity register(UserEntity userEntity);
    UserEntity login(UserEntity userEntity);
}
