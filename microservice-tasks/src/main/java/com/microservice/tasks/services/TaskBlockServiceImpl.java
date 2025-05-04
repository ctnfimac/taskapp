package com.microservice.tasks.services;

import com.microservice.tasks.connector.UserConnector;
import com.microservice.tasks.connector.response.UserDTO;
import com.microservice.tasks.enums.APIError;
import com.microservice.tasks.exception.GlobalTaskException;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.repositories.TaskBlockRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TaskBlockServiceImpl implements TaskBlockService{

    private UserConnector userConnector;

    private TaskBlockRepository taskBlockRepository;

    @Override
    public TaskBlockEntity create(TaskBlockEntity taskBlockEntity) {
        // verifico que el usuario no tenga ningun bloque de tarea sin finalizar
        if(taskBlockRepository.existsByUserIdAndDoneFalse(taskBlockEntity.getUserId())){
            throw new GlobalTaskException(APIError.TASK_BLOCK_ACTIVE);
        }

        // verifico si el usuario existe
        checkUserExists(taskBlockEntity);

        // guardo el nuevo bloque de tarea
        return taskBlockRepository.save(taskBlockEntity);
    }

    private void checkUserExists(TaskBlockEntity taskBlockEntity){
        UserDTO user = userConnector.getUser(taskBlockEntity.getUserId());
        if(user == null)
            throw new GlobalTaskException(APIError.USER_NOT_FOUND);
    }
}
