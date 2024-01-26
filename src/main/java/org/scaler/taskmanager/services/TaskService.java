package org.scaler.taskmanager.services;

import org.scaler.taskmanager.entities.TaskEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Service
public class TaskService {
    private ArrayList<TaskEntity> tasks = new ArrayList<>();
    private SimpleDateFormat deadLineformatter = new SimpleDateFormat("yyyy-MM-dd");
    private int taskId = 1;

    public TaskEntity addTask(String title, String description, String deadLine) throws ParseException {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setTitle(title);
        taskEntity.setId(taskId);
        taskEntity.setDescription(description);
        taskEntity.setDeadLine(deadLineformatter.parse(deadLine));
        taskEntity.setCompleted(false);
        tasks.add(taskEntity);
        taskId++;
        return taskEntity;
    }

    public ArrayList<TaskEntity> getTasks() {
        return tasks;
    }
    public TaskEntity getTaskById(int id) {
        for(TaskEntity task : tasks) {
            if(task.getId() == id) {
                return task;
            }
        }
        return null;
    }
    public TaskEntity updateTask(int id, String description, String deadline, Boolean completed) throws ParseException{
        TaskEntity task = getTaskById(id);
        if(task == null) {
            return null;
        }
        if(description != null) {
            task.setDescription(description);
        }
        if(deadline != null) {
            task.setDeadLine(deadLineformatter.parse(deadline));
        }
        if(completed != null) {
            task.setCompleted(completed);
        }
        return task;
    }
}
