package com.microservice.tasks.repositories;

import com.microservice.tasks.models.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
}
