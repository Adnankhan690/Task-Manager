package org.scaler.taskmanager.controllers;

import org.scaler.taskmanager.dto.CreateTaskDTO;
import org.scaler.taskmanager.entities.TaskEntity;
import org.scaler.taskmanager.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/tasks")
public class TaskController {
    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTask() {
        var task = taskService.getTasks();
        return ResponseEntity.ok(task);
    }
    @GetMapping("{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Integer id) {
        var task = taskService.getTaskById(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadLine());

        return ResponseEntity.ok(task);
    }
}
