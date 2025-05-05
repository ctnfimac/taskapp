package com.microservice.tasks.services;

import com.microservice.tasks.connector.UserConnector;
import com.microservice.tasks.connector.response.UserDTO;
import com.microservice.tasks.dto.task.TaskRequestCreate;
import com.microservice.tasks.enums.APIError;
import com.microservice.tasks.exception.GlobalTaskException;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.models.TaskEntity;
import com.microservice.tasks.repositories.TaskBlockRepository;
import com.microservice.tasks.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskBlockServiceImpl implements TaskBlockService{

    private UserConnector userConnector;

    private TaskBlockRepository taskBlockRepository;
    private TaskRepository taskRepository;

    @Override
    public TaskBlockEntity create(TaskBlockEntity taskBlockEntity) {
        // verifico que el usuario no tenga ningun bloque de tarea sin finalizar
        if(taskBlockRepository.existsByUserIdAndDoneFalse(taskBlockEntity.getUserId())){
            throw new GlobalTaskException(APIError.TASK_BLOCK_ACTIVE);
        }

        // verifico si el usuario existe en el microservicio de usuarios
        checkUserExists(taskBlockEntity);

        // guardo el nuevo bloque de tarea
        return taskBlockRepository.save(taskBlockEntity);
    }


    @Override
    public TaskEntity createTask(Long blockId, TaskRequestCreate taskRequestCreate) {
        // verifico que el bloque de tareas exista y no este finalizado
        TaskBlockEntity taskBlock = taskBlockRepository.findById(blockId)
                .orElseThrow(() -> new GlobalTaskException(APIError.TASK_BLOCK_NOT_FOUND));

        if(taskBlock.getDone()){
            throw new GlobalTaskException(APIError.TASK_BLOCK_FINISHED);
        }

        // verifico que coincidan el taskRequestCreate.userId y el userId del block relacionado
        // con el blockId ingresado
        if(!taskBlock.getUserId().equals(taskRequestCreate.getUserId())){
            throw new GlobalTaskException(APIError.USER_DIFFERENT);
        }

        // verifico que el usuario existe en el microservicio de usuarios
        checkUserExistsById(taskRequestCreate.getUserId());

        // Doy de alta la nueva tarea
        TaskEntity task = TaskEntity.builder()
                .title(taskRequestCreate.getTitle())
                .taskBlockEntity(taskBlock)
                .build();

        return taskRepository.save(task);
    }

    private void checkUserExists(TaskBlockEntity taskBlockEntity){
        UserDTO user = userConnector.getUser(taskBlockEntity.getUserId());
        if(user == null)
            throw new GlobalTaskException(APIError.USER_NOT_FOUND);
    }

    private void checkUserExistsById(Long userId){
        UserDTO user = userConnector.getUser(userId);
        if(user == null)
            throw new GlobalTaskException(APIError.USER_NOT_FOUND);
    }
}
