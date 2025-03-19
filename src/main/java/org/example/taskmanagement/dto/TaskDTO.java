package org.example.taskmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NonNull
public class TaskDTO {
    private String heading;
}
