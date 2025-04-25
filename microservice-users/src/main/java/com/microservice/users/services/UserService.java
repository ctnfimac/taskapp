package com.microservice.users.services;

import com.microservice.users.entities.UserEntity;
import java.util.List;

public interface UserService {
    List<UserEntity> getAll();
}
