package com.microservice.tasks.controllers;


import com.microservice.tasks.dto.taskblock.RequestCreateTaskBlockDTO;
import com.microservice.tasks.dto.taskblock.ResponseCreateTaskBlockDTO;
import com.microservice.tasks.mappers.TaskBlockMapper;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.services.TaskBlockService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/block")
@AllArgsConstructor
public class TaskBlockController {

    @Autowired
    private TaskBlockService taskBlockService;

    private TaskBlockMapper taskBlockMapper;

    @PostMapping
    public ResponseEntity<ResponseCreateTaskBlockDTO> create(@RequestBody @Valid RequestCreateTaskBlockDTO requestCreateTaskBlockDTO){
        TaskBlockEntity taskBlockEntity = taskBlockMapper.requestCreateTaskBlockDTOtoTaskBlockEntity(requestCreateTaskBlockDTO);
        TaskBlockEntity taskBlockCreated = taskBlockService.create(taskBlockEntity);
        return new ResponseEntity<>(
                taskBlockMapper.taskBlockEntityToResponseCreateTaskBlockDTO(taskBlockCreated),
                HttpStatus.CREATED);
    }
}
