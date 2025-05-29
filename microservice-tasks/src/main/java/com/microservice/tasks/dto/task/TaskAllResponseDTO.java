package com.microservice.tasks.dto.task;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TaskAllResponseDTO {
    private Long idTaskBlock;
    private String titleBlock;
    private List<TaskResponseDTO> listTasks;
}
