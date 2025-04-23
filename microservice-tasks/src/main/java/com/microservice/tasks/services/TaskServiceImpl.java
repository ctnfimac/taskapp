package com.microservice.tasks.services;

import com.microservice.tasks.models.TaskEntity;
import com.microservice.tasks.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{

    @Autowired
    private TaskRepository taskRepository;


    @Override
    public List<TaskEntity> getAll() {
        return taskRepository.findAll();
    }
}
