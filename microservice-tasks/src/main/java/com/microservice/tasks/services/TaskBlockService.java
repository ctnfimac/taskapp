package com.microservice.tasks.services;

import com.microservice.tasks.dto.task.TaskRequestCreate;
import com.microservice.tasks.dto.taskblock.RequestUpdateDoneTaskBlockDTO;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.models.TaskEntity;
import java.util.List;

public interface TaskBlockService {
    TaskBlockEntity create(TaskBlockEntity taskBlockEntity);
    TaskEntity createTask(Long blockId, TaskRequestCreate taskRequestCreate);
    void deleteTask(Long blockId, Long taskId);
    TaskBlockEntity setDoneTrue(Long blockId, RequestUpdateDoneTaskBlockDTO user);
    List<TaskBlockEntity> getAllByUserId(Long userId);
    void publisher(Long userId, TaskBlockEntity taskBlock);
    TaskBlockEntity findById(Long id);
    TaskBlockEntity findByUserAndDoneFalse(Long userId);
    boolean hasBlockActive(Long userId);
}
