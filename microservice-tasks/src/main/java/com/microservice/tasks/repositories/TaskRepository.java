package com.microservice.tasks.repositories;

import com.microservice.tasks.models.TaskBlockEntity;
import com.microservice.tasks.models.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {
    Optional<TaskEntity> findByIdAndTaskBlockEntity(Long taskId, TaskBlockEntity taskBlockEntity);

    @Query("""
        SELECT tb
        FROM TaskBlockEntity tb
        WHERE tb.id = :blockId
        AND NOT EXISTS (
            SELECT t FROM TaskEntity t
            WHERE t.taskBlockEntity.id = tb.id AND t.done = false
        )
    """)
    Optional<TaskBlockEntity> findByBlockIdAndAllTasksDone(@Param("blockId") Long blockId);


    @Query("""
        SELECT t
        FROM TaskEntity t
        WHERE t.taskBlockEntity.id = :blockId
        AND t.taskBlockEntity.userId = :userId
        AND t.taskBlockEntity.done = true
    """)
    List<TaskEntity> findTasksByBlockFinishedAndIdUserId(@Param("userId") Long userId, @Param("blockId") Long blockId);

    List<TaskEntity> findByTaskBlockEntityId(Long taskBlockId);
}
