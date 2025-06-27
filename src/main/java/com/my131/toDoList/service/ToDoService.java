package com.my131.toDoList.service;

import org.springframework.stereotype.Service;

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

    public void addTask(String description) {
        tasks.add(new Task(idCounter.getAndIncrement(), description));

//        for(Task task:tasks) {
//            System.out.println(task.getId() + task.getDescription() + task.isCompleted());
//        }
    }
    public void deleteTask(int taskId) {
        System.out.println("delete task");
        tasks.removeIf(t -> t.getId() == taskId);
    }

    public static class Task {      // Model = entity = domain 을 이너클래스로 작성, 분리 작성 가능
        private final int id;
        private String description;
        private boolean completed = false;

        public Task(int id, String description) {
            this.id = id;
            this.description = description;
        }

        public int getId() { return id; }
        public String getDescription() { return description; }
        public boolean isCompleted() { return completed; }

        public void setDescription(String description) {
            this.description = description;
        }
        public void setCompleted(boolean completed) {
            this.completed = completed;
        }
    }
}
