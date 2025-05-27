package com.microservice.tasks.controllers;

import com.microservice.tasks.dto.task.*;
import com.microservice.tasks.mappers.TaskMapper;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.models.TaskEntity;
import com.microservice.tasks.services.TaskBlockService;
import com.microservice.tasks.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController implements TaskControllerSwagger{

    @Autowired
    private TaskService taskService;

    private TaskBlockService taskBlockService;

    private TaskMapper taskMapper;


    @PatchMapping("{taskId}/toogle-done")
    public ResponseEntity<TaskResponseUpdateDone> toogleDoneTask(@PathVariable Long taskId,
                                                                 @RequestBody @Valid TaskRequestUpdateDone taskRequestUpdateDone){
        TaskEntity taskEntity = taskService.toogleDoneTask(taskId, taskRequestUpdateDone);
        return new ResponseEntity<>(taskMapper.taskEntityToTaskResponseUpdateDone(taskEntity), HttpStatus.OK);
    }

    @GetMapping("{blockId}/{userId}")
    public ResponseEntity<List<TaskResponseDTO>> getTaskByUserAndBlockFinished(@PathVariable Long blockId, @PathVariable Long userId){
        List<TaskResponseDTO> taskResponseDTOS = taskService.findByUserAndBLockFinished(userId, blockId).stream()
                .map(taskMapper::taskEntityToTaskResponseDTO)
                .toList();
        return new ResponseEntity<>(taskResponseDTOS, HttpStatus.OK);
    }

    @Override
    @GetMapping("/{userId}/all")
    public ResponseEntity<TaskAllResponseDTO> getTaskByUserAndBlockActive(Long userId) {
        TaskAllResponseDTO taskAllResponse = new TaskAllResponseDTO();

        List<TaskResponseDTO> taskResponseDTOS = taskService.findByUserAndBLockActive(userId).stream()
                .map(taskMapper::taskEntityToTaskResponseDTO)
                .toList();

        taskAllResponse.setListTasks(taskResponseDTOS);

        TaskBlockEntity taskBlock = taskBlockService.findByUserAndDoneFalse(userId);
        taskAllResponse.setTitleBlock(taskBlock.getTitle());

        return new ResponseEntity<>(taskAllResponse, HttpStatus.OK);
    }
}
