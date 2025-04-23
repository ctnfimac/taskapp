package com.microservice.tasks.services;

import com.microservice.tasks.models.TaskEntity;

import java.util.List;

public interface TaskService {
    List<TaskEntity> getAll();
}
