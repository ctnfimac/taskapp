package com.microservice.tasks.services;

import com.microservice.tasks.dto.task.TaskRequestUpdateDone;
import com.microservice.tasks.models.TaskEntity;

public interface TaskService {
    TaskEntity toogleDoneTask(Long taskId, TaskRequestUpdateDone taskRequestUpdateDone);
}
