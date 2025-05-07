package com.microservice.tasks.repositories;

import com.microservice.tasks.models.TaskBlockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskBlockRepository extends JpaRepository<TaskBlockEntity, Long> {
    // busco bloque de tarea por userId y por done con valor true
    boolean existsByUserIdAndDoneFalse(Long userId);
    List<TaskBlockEntity> findByUserId(Long userId);
}
