package com.microservice.tasks.services;

import com.microservice.tasks.dto.task.TaskRequestUpdateDone;
import com.microservice.tasks.models.TaskEntity;
import java.util.List;

public interface TaskService {
    TaskEntity toogleDoneTask(Long taskId, TaskRequestUpdateDone taskRequestUpdateDone);
    List<TaskEntity> findByUserAndBLock(Long userId, Long blockId);
}
