package com.microservice.tasks.mappers;

import com.microservice.tasks.dto.taskblock.RequestCreateTaskBlockDTO;
import com.microservice.tasks.models.TaskBlockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskBlockMapper {
    TaskBlockEntity requestCreateTaskBlockDTOtoTaskBlockEntity(RequestCreateTaskBlockDTO requestCreateTaskBlockDTO);
}
