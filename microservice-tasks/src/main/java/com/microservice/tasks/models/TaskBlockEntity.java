package com.microservice.tasks.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "task_block")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskBlockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "title no puede estar vacio")
    private String title;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean done;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(nullable = false, name = "user_id")
    private Long userId;

    @PrePersist
    public void prePersist() {
        if (done == null) done = false;
    }
}
