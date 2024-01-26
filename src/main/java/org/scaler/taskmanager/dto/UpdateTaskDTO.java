package org.scaler.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateTaskDTO {
    String description;
    String deadline;
    Boolean completed;
}
