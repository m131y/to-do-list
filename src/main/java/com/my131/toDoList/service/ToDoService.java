package com.my131.toDoList.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ToDoService {
    private final List<Task> tasks = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(1);

    public List<Task> getAllTasks() {
        return tasks;
    }

    public List<Task> getAllSorted(String sort) {
        List<Task> copy = new ArrayList<>(tasks);

        switch (sort) {
            case "color" -> copy.sort(Comparator.comparing(Task::getColor, Comparator.nullsLast(String::compareTo)));
            case "start" -> copy.sort(Comparator.comparing(Task::getTaskStartDate, Comparator.nullsLast(LocalDate::compareTo)));
        }
        return copy;
    }



    public void addTask(String description, String color, LocalDate taskStartDate, LocalDate taskEndDate) {
        tasks.add(new Task(idCounter.getAndIncrement(), description, color, taskStartDate, taskEndDate));
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
        private String color;
        private boolean completed = false;
        private LocalDate taskStartDate;
        private LocalDate taskEndDate;

        public Task(int id, String description, String color, LocalDate taskStartDate, LocalDate taskEndDate) {
            this.id = id;
            this.description = description;
            this.color = color;
            this.taskStartDate = taskStartDate;
            this.taskEndDate = taskEndDate;
        }

        public int getId() { return id; }
        public String getDescription() { return description; }
        public String getColor() { return color; }
        public LocalDate getTaskStartDate() { return taskStartDate; }
        public LocalDate getTaskEndDate() { return taskEndDate; }
        public boolean isCompleted() { return completed; }

        public void setDescription(String description) {
            this.description = description;
        }
        public void setColor(String color) { this.color = color; }
        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
        public void setTaskStartDate(LocalDate taskStartDate) {
            this.taskStartDate = taskStartDate;
        }
        public void setTaskEndDate(LocalDate taskEndDate) {
            this.taskEndDate = taskEndDate;
        }
    }
}
