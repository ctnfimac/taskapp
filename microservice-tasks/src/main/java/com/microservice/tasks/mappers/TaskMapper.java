package com.microservice.tasks.mappers;

import com.microservice.tasks.dto.task.TaskResponseCreate;
import com.microservice.tasks.models.TaskEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskMapper {
    TaskResponseCreate taskEntityToTaskResponseCreate(TaskEntity taskEntity);
}
