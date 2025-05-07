package com.microservice.tasks.mappers;

import com.microservice.tasks.dto.task.TaskResponseCreate;
import com.microservice.tasks.dto.task.TaskResponseDTO;
import com.microservice.tasks.dto.task.TaskResponseUpdateDone;
import com.microservice.tasks.models.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    TaskResponseCreate taskEntityToTaskResponseCreate(TaskEntity taskEntity);

    @Mapping(source = "id", target= "taskId")
    @Mapping(source = "title", target= "title")
    @Mapping(source = "done", target= "done")
    TaskResponseUpdateDone taskEntityToTaskResponseUpdateDone(TaskEntity taskEntity);

    TaskResponseDTO taskEntityToTaskResponseDTO(TaskEntity taskEntity);
}
