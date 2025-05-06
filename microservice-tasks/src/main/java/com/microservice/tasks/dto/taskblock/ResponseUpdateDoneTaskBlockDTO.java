package com.microservice.tasks.dto.taskblock;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseUpdateDoneTaskBlockDTO {
    private Long id;
    private String title;
    private Boolean done;
}
