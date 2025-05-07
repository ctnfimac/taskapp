package com.microservice.users.services;

import com.microservice.users.connector.TaskBlockConnector;
import com.microservice.users.connector.TaskConnector;
import com.microservice.users.connector.response.TaskBlockDTO;
import com.microservice.users.connector.response.TaskDTO;
import com.microservice.users.entities.UserEntity;
import com.microservice.users.enums.APIError;
import com.microservice.users.exceptions.APIExceptionHandler;
import com.microservice.users.exceptions.GlobalTaskException;
import com.microservice.users.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    private TaskBlockConnector taskBlockConnector;

    private TaskConnector taskConnector;

    @Override
    public Optional<UserEntity> getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<TaskBlockDTO> getTaskBlockByUser(Long userId) {
        // verifico que el usuario ingresado exista
        if(!userRepository.existsById(userId)){
            throw new GlobalTaskException(APIError.USER_NOT_FOUND);
        }

        // Traigo los bloques de tarea del microservicio de tasks
        return taskBlockConnector.getTaskBlocks(userId);
    }

    @Override
    public List<TaskDTO> getTaskByUserAndBlock(Long blockId, Long userId) {
        // verifico que el usuario ingresado exista
        if(!userRepository.existsById(userId)){
            throw new GlobalTaskException(APIError.USER_NOT_FOUND);
        }

        // Traigo las tareas del microservicio de tasks
        return taskConnector.getTasksByUserAndBlock(blockId, userId);
    }

}
