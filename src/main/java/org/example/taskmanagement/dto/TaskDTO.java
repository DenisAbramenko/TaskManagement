package org.example.taskmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.example.taskmanagement.entity.Priority;
import org.example.taskmanagement.entity.Status;

@Data
@AllArgsConstructor
@NoArgsConstructor
@NonNull
public class TaskDTO {
    @NonNull
    private String heading;
    private String description;
    private Status status;  // WAITING, IN_PROGRESS, COMPLETED
    private Priority priority;  // HIGH, MEDIUM, LOW
    private String comments;
    private String author;
    private String executor;
}
