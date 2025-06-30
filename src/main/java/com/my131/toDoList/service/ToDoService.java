package com.my131.toDoList.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ToDoService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(String description, LocalDate taskDate) {
        tasks.add(new Task(idCounter.getAndIncrement(), description, taskDate));
    }
    public void toggleTaskCompletion(int taskId) {
        for (Task task:tasks) {
            if (task.getId() == taskId) {
                task.setCompleted(!task.isCompleted());

                break;
            }
        }
    }

    public void UpdateTask(int taskId, String newDescription) {
        for (Task task:tasks) {
            if (task.getId() == taskId) {
                task.setDescription(newDescription);

                break;
            }
        }
    }


    public void deleteTask(int taskId) {
        tasks.removeIf(t -> t.getId() == taskId);
    }

    public static class Task {      // Model = entity = domain 을 이너클래스로 작성, 분리 작성 가능
        private final int id;
        private String description;
        private boolean completed = false;
        private LocalDate taskDate;

        public Task(int id, String description, LocalDate taskDate) {
            this.id = id;
            this.description = description;
            this.taskDate = taskDate;
        }

        public int getId() { return id; }
        public String getDescription() { return description; }
        public LocalDate getTaskDate() { return taskDate; }
        public boolean isCompleted() { return completed; }

        public void setDescription(String description) {
            this.description = description;
        }
        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
        public void setTaskDate(LocalDate taskDate) {
            this.taskDate = taskDate;
        }
    }
}
