package com.microservice.tasks.services;

import com.microservice.tasks.config.RabbitMQConfig;
import com.microservice.tasks.connector.UserConnector;
import com.microservice.tasks.connector.response.UserDTO;
import com.microservice.tasks.dto.rabbitmq.AllTasksCompletedEventDTO;
import com.microservice.tasks.dto.task.TaskRequestCreate;
import com.microservice.tasks.dto.taskblock.RequestUpdateDoneTaskBlockDTO;
import com.microservice.tasks.enums.APIError;
import com.microservice.tasks.exception.GlobalTaskException;
import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.models.TaskEntity;
import com.microservice.tasks.repositories.TaskBlockRepository;
import com.microservice.tasks.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskBlockServiceImpl implements TaskBlockService{

    private UserConnector userConnector;
    private TaskBlockRepository taskBlockRepository;
    private TaskRepository taskRepository;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public TaskBlockEntity create(TaskBlockEntity taskBlockEntity) {
        // verifico que el usuario no tenga ningun bloque de tarea sin finalizar
        if(taskBlockRepository.existsByUserIdAndDoneFalse(taskBlockEntity.getUserId())){
            throw new GlobalTaskException(APIError.TASK_BLOCK_ACTIVE);
        }

        checkUserExists(taskBlockEntity);

        // guardo el nuevo bloque de tarea
        return taskBlockRepository.save(taskBlockEntity);
    }


    @Override
    public TaskEntity createTask(Long blockId, TaskRequestCreate taskRequestCreate) {
        TaskBlockEntity taskBlock = checkTaskBlockExistsAndDoneIsFalse(blockId);

        // verifico que coincidan el taskRequestCreate.userId y el userId del block relacionado
        // con el blockId ingresado
        if(!taskBlock.getUserId().equals(taskRequestCreate.getUserId())){
            throw new GlobalTaskException(APIError.USER_DIFFERENT);
        }

        checkUserExistsById(taskRequestCreate.getUserId());

        // Doy de alta la nueva tarea
        TaskEntity task = TaskEntity.builder()
                .title(taskRequestCreate.getTitle())
                .taskBlockEntity(taskBlock)
                .build();

        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long blockId, Long taskId) {
        TaskBlockEntity taskBlock = checkTaskBlockExistsAndDoneIsFalse(blockId);

        // verifico que exista la tarea del bloque
        TaskEntity task = taskRepository.findByIdAndTaskBlockEntity(taskId, taskBlock)
                .orElseThrow(() -> new GlobalTaskException(APIError.TASK_NOT_FOUND));

        // TODO tendría que corroborar la veracidad del usuario, esto podría hacerse con tokens

        checkUserExistsById(taskBlock.getUserId());

        //elimino el usuario
        taskRepository.delete(task);
    }

    @Override
    public TaskBlockEntity setDoneTrue(Long blockId, RequestUpdateDoneTaskBlockDTO user) {
        TaskBlockEntity taskBlock = checkTaskBlockExistsAndDoneIsFalse(blockId);

        // verifico que el usuario del blockId ingresado coincida con el del user
        if(!taskBlock.getUserId().equals(user.getUserId())){
            throw new GlobalTaskException(APIError.USER_DIFFERENT);
        }

        // verifico que todas las tareas vinculadas al bloque esten con done = true
        taskRepository.findByBlockIdAndAllTasksDone(blockId)
                 .orElseThrow(() -> new GlobalTaskException(APIError.TASK_NOT_DONE));

        // actualizo el valor de done del bloque de tareas
        taskBlock.setDone(true);

        //TODO: acá tengo que enviar la señal al sistema de mensajeria
        publisher(user.getUserId(), taskBlock);

        return taskBlockRepository.save(taskBlock);
    }



    @Override
    public List<TaskBlockEntity> getAllByUserId(Long userId) {
        return taskBlockRepository.findByUserIdAndDoneTrue(userId);
    }

    @Override
    public void publisher(Long userId, TaskBlockEntity taskBlock) {
        AllTasksCompletedEventDTO taskCompletedDto = new AllTasksCompletedEventDTO();

        UserDTO userDTO = checkUserExistsById(userId);
        taskCompletedDto.setUserEmail(userDTO.getEmail());
        taskCompletedDto.setTaskBlockTitle(taskBlock.getTitle());
        taskCompletedDto.setCreatedAt(taskBlock.getCreatedAt());

        List<String> taskList = taskRepository.findByTaskBlockEntityId(taskBlock.getId())
                .stream()
                .map(TaskEntity::getTitle)
                .toList();
        taskCompletedDto.setTaskTitles(taskList);

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.ROUTING_KEY,
                taskCompletedDto
        );
    }

    /**
     * verifico que el usuario existe en el microservicio de usuarios
     * @param taskBlockEntity TaskEntity
     */
    private void checkUserExists(TaskBlockEntity taskBlockEntity){
        UserDTO user = userConnector.getUser(taskBlockEntity.getUserId());
        if(user == null)
            throw new GlobalTaskException(APIError.USER_NOT_FOUND);
    }

    /**
     * verifico que el usuario existe en el microservicio de usuarios
     * @param userId Long
     */
    private UserDTO checkUserExistsById(Long userId){
        UserDTO user = userConnector.getUser(userId);
        if(user == null)
            throw new GlobalTaskException(APIError.USER_NOT_FOUND);

        return user;
    }

    /**
    * Verifico que el bloque de tareas exista y no este finalizado
     * @param blockId Long
    */
    private TaskBlockEntity checkTaskBlockExistsAndDoneIsFalse(Long blockId){
        TaskBlockEntity taskBlock = taskBlockRepository.findById(blockId)
                .orElseThrow(() -> new GlobalTaskException(APIError.TASK_BLOCK_NOT_FOUND));

        if(taskBlock.getDone()){
            throw new GlobalTaskException(APIError.TASK_BLOCK_FINISHED);
        }

        return taskBlock;
    }
}
