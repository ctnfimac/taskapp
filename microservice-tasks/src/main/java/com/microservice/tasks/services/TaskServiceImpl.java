package com.microservice.tasks.services;

import com.microservice.tasks.connector.UserConnector;
import com.microservice.tasks.connector.response.UserDTO;
import com.microservice.tasks.dto.task.TaskRequestUpdateDone;
import com.microservice.tasks.enums.APIError;
import com.microservice.tasks.exception.GlobalTaskException;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.models.TaskEntity;
import com.microservice.tasks.repositories.TaskBlockRepository;
import com.microservice.tasks.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService{

    private UserConnector userConnector;
    private TaskRepository taskRepository;
    private TaskBlockRepository taskBlockRepository;
    private TaskBlockService taskBlockService;


    @Override
    public TaskEntity toogleDoneTask(Long taskId, TaskRequestUpdateDone taskRequestUpdateDone) {
        // verifico que el bloque de tareas exista y no este finalizado
        TaskBlockEntity taskBlock = taskBlockRepository.findById(taskRequestUpdateDone.getTaskBlockId())
                .orElseThrow(() -> new GlobalTaskException(APIError.TASK_BLOCK_NOT_FOUND));

        if(taskBlock.getDone()) throw new GlobalTaskException(APIError.TASK_BLOCK_FINISHED);

        // verifico que la tarea existe y pertenece al bloque ingresado
        TaskEntity task = taskRepository.findByIdAndTaskBlockEntity(taskId, taskBlock)
                .orElseThrow(() -> new GlobalTaskException(APIError.TASK_NOT_FOUND));

        // Valido el usuario
        checkUserExistsById(taskRequestUpdateDone.getUserId());

        task.setDone(!task.getDone());
        taskRepository.save(task);
        return task;
    }

    @Override
    public List<TaskEntity> findByUserAndBLockFinished(Long userId, Long blockId) {
        return taskRepository.findTasksByBlockFinishedAndIdUserId(userId, blockId);
    }

    @Override
    public List<TaskEntity> findByUserAndBLockActive(Long userId) {
        //TaskBlockEntity taskBlock = taskBlockRepository.findById(blockId)
        //        .orElseThrow(() -> new GlobalTaskException(APIError.TASK_BLOCK_NOT_FOUND));

        // verifico que el usuario del blockId ingresado coincida con el del user
        //if(!taskBlock.getUserId().equals(userId)){
          //  throw new GlobalTaskException(APIError.USER_DIFFERENT);
       // }

        // Valido el usuario
        checkUserExistsById(userId);

        return taskRepository.findByTaskBlockEntityDoneFalseAndTaskBlockEntityUserIdOrderByIdAsc(userId);
    }


    private void checkUserExistsById(Long userId){
        UserDTO user = userConnector.getUser(userId);
        if(user == null)
            throw new GlobalTaskException(APIError.USER_NOT_FOUND);
    }
}
