package com.microservice.tasks.controllers;


import com.microservice.tasks.dto.task.TaskRequestCreate;
import com.microservice.tasks.dto.task.TaskResponseCreate;
import com.microservice.tasks.dto.taskblock.*;
import com.microservice.tasks.mappers.TaskBlockMapper;
import com.microservice.tasks.mappers.TaskMapper;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.models.TaskEntity;
import com.microservice.tasks.services.TaskBlockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/block")
@AllArgsConstructor
public class TaskBlockController {

    @Autowired
    private TaskBlockService taskBlockService;

    private TaskBlockMapper taskBlockMapper;
    private TaskMapper taskMapper;

    @PostMapping
    public ResponseEntity<ResponseCreateTaskBlockDTO> create(@RequestBody @Valid RequestCreateTaskBlockDTO requestCreateTaskBlockDTO){
        TaskBlockEntity taskBlockEntity = taskBlockMapper.requestCreateTaskBlockDTOtoTaskBlockEntity(requestCreateTaskBlockDTO);
        TaskBlockEntity taskBlockCreated = taskBlockService.create(taskBlockEntity);
        return new ResponseEntity<>(
                taskBlockMapper.taskBlockEntityToResponseCreateTaskBlockDTO(taskBlockCreated),
                HttpStatus.CREATED);
    }

    @PostMapping("{block_id}/task")
    public ResponseEntity<TaskResponseCreate> createTask(@PathVariable("block_id") Long blockId,
                                                         @RequestBody @Valid TaskRequestCreate taskRequestCreate){
        TaskEntity taskCreated = taskBlockService.createTask(blockId, taskRequestCreate);
        return new ResponseEntity<>(
                taskMapper.taskEntityToTaskResponseCreate(taskCreated),
                HttpStatus.CREATED);
    }

    @DeleteMapping("{blockId}/task/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable("blockId") Long blockId,
                                           @PathVariable("taskId") Long taskId){
        taskBlockService.deleteTask(blockId, taskId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("{blockId}/finish")
    public ResponseEntity<ResponseUpdateDoneTaskBlockDTO> finishTaskBlock(@PathVariable Long blockId,
                                                                          @RequestBody RequestUpdateDoneTaskBlockDTO taskBlockDTO){
        TaskBlockEntity taskBlock = taskBlockService.setDoneTrue(blockId, taskBlockDTO);
        return new ResponseEntity<>(
                taskBlockMapper.taskBlockEntityToResponseUpdateDoneTaskBlockDTO(taskBlock),
                HttpStatus.OK
        );
    }

    @GetMapping("{userId}")
    public ResponseEntity<List<ResponseTaskBlockDTO>> getAllTasksByUserId(@PathVariable("userId") Long userId){
        List<ResponseTaskBlockDTO> taskBlocks = taskBlockService.getAllByUserId(userId).stream()
                .map(taskBlockMapper::taskBlockEntityToResponseTaskBlockDTO)
                .toList();

        return new ResponseEntity<>(taskBlocks, HttpStatus.OK);
    }
}
