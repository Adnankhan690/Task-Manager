package org.scaler.taskmanager.controllers;

import org.scaler.taskmanager.dto.CreateTaskDTO;
import org.scaler.taskmanager.dto.ErrorResponseDTO;
import org.scaler.taskmanager.dto.UpdateTaskDTO;
import org.scaler.taskmanager.entities.TaskEntity;
import org.scaler.taskmanager.services.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping ("/tasks")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("")
    public ResponseEntity<List<TaskEntity>> getTask() {
        var task = taskService.getTasks();
        return ResponseEntity.ok(task);
    }
    @GetMapping("{id}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable("id") Integer id) {
        var task = taskService.getTaskById(id);
        if(task == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }
    @PostMapping("")
    public ResponseEntity<TaskEntity> addTask(@RequestBody CreateTaskDTO body) throws ParseException {
        var task = taskService.addTask(body.getTitle(), body.getDescription(), body.getDeadLine());

        return ResponseEntity.ok(task);
    }
    @PatchMapping("{id}")
    public ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody UpdateTaskDTO body) throws ParseException{
        var task = taskService.updateTask(id, body.getDescription(), body.getDeadline(), body.getCompleted());
        if(task == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(task);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e) {
        if(e instanceof ParseException) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format"));
        }
        //this prints to the developer side not the user-side
        e.printStackTrace();
        return ResponseEntity.internalServerError().body(new ErrorResponseDTO("Internal Server Error"));
    }
}
