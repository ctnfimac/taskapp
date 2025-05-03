package com.microservice.tasks.services;

import com.microservice.tasks.connector.UserConnector;
import com.microservice.tasks.connector.response.UserDTO;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.repositories.TaskBlockRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskBlockServiceImpl implements TaskBlockService{

    private UserConnector userConnector;

    private TaskBlockRepository taskBlockRepository;

    @Override
    public TaskBlockEntity create(TaskBlockEntity taskBlockEntity) {
        System.out.println("Id buscado: " + taskBlockEntity.getUserId());
        UserDTO user = userConnector.getUser(taskBlockEntity.getUserId());
        System.out.println("Usuario encontrado:");
        System.out.println("email:" + user.getEmail()+ ",id: " + user.getId());
        return null;
    }
}
