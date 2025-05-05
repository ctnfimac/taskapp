package com.microservice.tasks.controllers;

import com.microservice.tasks.dto.task.TaskRequestCreate;
import com.microservice.tasks.dto.task.TaskRequestUpdateDone;
import com.microservice.tasks.dto.task.TaskResponseUpdateDone;
import com.microservice.tasks.mappers.TaskMapper;
import com.microservice.tasks.models.TaskEntity;
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
public class TaskController {

    @Autowired
    private TaskService taskService;

    private TaskMapper taskMapper;


    @PatchMapping("{taskId}/toogle-done")
    public ResponseEntity<TaskResponseUpdateDone> toogleDoneTask(@PathVariable Long taskId,
                                                                 @RequestBody @Valid TaskRequestUpdateDone taskRequestUpdateDone){
        TaskEntity taskEntity = taskService.toogleDoneTask(taskId, taskRequestUpdateDone);
        return new ResponseEntity<>(taskMapper.taskEntityToTaskResponseUpdateDone(taskEntity), HttpStatus.OK);
    }
}
