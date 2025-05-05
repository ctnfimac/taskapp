package com.microservice.tasks.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String title;

    @Column(columnDefinition = "BOOLEAN DEFAULT false")
    private Boolean done;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "task_block_id", referencedColumnName = "id")
    private TaskBlockEntity taskBlockEntity;

    @PrePersist
    public void prePersist(){
        if(done == null) done = false;
    }

}
