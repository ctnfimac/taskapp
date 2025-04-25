package com.microservice.users.services;

import com.microservice.users.entities.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserEntity> getAll();
    Optional<UserEntity> getById(Long id);
    UserEntity create(UserEntity user);
    UserEntity update(Long id, UserEntity user);
    boolean delete(Long id);
}
