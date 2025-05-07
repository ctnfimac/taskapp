package com.microservice.tasks.mappers;

import com.microservice.tasks.dto.taskblock.RequestCreateTaskBlockDTO;
import com.microservice.tasks.dto.taskblock.ResponseCreateTaskBlockDTO;
import com.microservice.tasks.dto.taskblock.ResponseTaskBlockDTO;
import com.microservice.tasks.dto.taskblock.ResponseUpdateDoneTaskBlockDTO;
import com.microservice.tasks.models.TaskBlockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface TaskBlockMapper {

    @Mapping(source = "title", target= "title")
    @Mapping(source = "userId", target= "userId")
    //@Mapping(target = "tokenActivation", ignore = true)
    TaskBlockEntity requestCreateTaskBlockDTOtoTaskBlockEntity(RequestCreateTaskBlockDTO requestCreateTaskBlockDTO);

    ResponseCreateTaskBlockDTO taskBlockEntityToResponseCreateTaskBlockDTO(TaskBlockEntity taskBlockEntity);

    ResponseUpdateDoneTaskBlockDTO taskBlockEntityToResponseUpdateDoneTaskBlockDTO(TaskBlockEntity taskBlockEntity);

    ResponseTaskBlockDTO taskBlockEntityToResponseTaskBlockDTO(TaskBlockEntity taskBlockEntity);
}
