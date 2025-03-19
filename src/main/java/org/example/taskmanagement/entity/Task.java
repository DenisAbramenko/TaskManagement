package org.example.taskmanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String heading;
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;  // WAITING, IN_PROGRESS, COMPLETED

    @Enumerated(EnumType.STRING)
    private Priority priority;  // HIGH, MEDIUM, LOW

    private String comments;
    private String author;
    private String executor;
}
