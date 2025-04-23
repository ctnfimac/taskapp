package com.microservice.tasks.controllers;

import com.microservice.tasks.models.TaskEntity;
import com.microservice.tasks.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;



@RestController
@RequestMapping("api/v1/tasks")
@AllArgsConstructor
public class TaskController {

    @Autowired
    private TaskService taskService;

   /* @GetMapping
    public List<TaskEntity> getTasks(){
        List<TaskEntity> listaTasks = taskService.getAll();
        return listaTasks;
    }*/

    @GetMapping
    public ResponseEntity<List<TaskEntity>> getTasks(){
        return new ResponseEntity<>(taskService.getAll(), HttpStatus.OK);
    }
}
