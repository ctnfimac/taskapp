package com.microservice.tasks.repositories;

import com.microservice.tasks.models.TaskBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskBlockRepository extends JpaRepository<TaskBlockEntity, Long> {
    // busco bloque de tarea por userId y por done con valor true
    boolean existsByUserIdAndDoneFalse(Long userId);
}
