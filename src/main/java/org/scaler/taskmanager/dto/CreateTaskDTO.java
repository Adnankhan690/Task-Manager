package org.scaler.taskmanager.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
public class CreateTaskDTO {
    private String title;
    private String description;
    private String deadLine;
}
