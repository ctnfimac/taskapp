package com.microservice.users.services;

import com.microservice.users.connector.response.TaskBlockDTO;
import com.microservice.users.entities.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserEntity> getById(Long id);
    List<TaskBlockDTO> getTaskBlockByUser(Long userId);
}
