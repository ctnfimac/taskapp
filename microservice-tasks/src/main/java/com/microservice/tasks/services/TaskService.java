package com.microservice.tasks.services;

import com.microservice.tasks.dto.task.TaskRequestUpdateDone;
import com.microservice.tasks.models.TaskEntity;
import java.util.List;

public interface TaskService {
    TaskEntity toogleDoneTask(Long taskId, TaskRequestUpdateDone taskRequestUpdateDone);
    List<TaskEntity> findByUserAndBLockFinished(Long userId, Long blockId);
    List<TaskEntity> findByUserAndBLockActive(Long userId);
}
