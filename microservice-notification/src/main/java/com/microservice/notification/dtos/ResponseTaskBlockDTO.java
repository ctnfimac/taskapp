package com.microservice.notification.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ResponseTaskBlockDTO {
    //private Long userId;
    private String userEmail;
    private String taskBlockTitle;
    private LocalDateTime createdAt;
    private List<String> taskTitles;
}
