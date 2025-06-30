package com.my131.toDoList.controller;

import com.my131.toDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;

@Controller
public class ToDoListController {
    @Autowired
    private ToDoService toDoService;

    @GetMapping("/")
    public String showToDoList(Model model) {
        model.addAttribute("todos", toDoService.getAllTasks());
        return "todolist";
    }

    @PostMapping("/todos")
    public String addTask(@RequestParam("task") String task, LocalDate taskDate) {  //html에서 post로 task인 값 받아서 String task에 저장
        toDoService.addTask(task, taskDate);

        return "redirect:/";
    }

    @PostMapping("/todos/toggle")
    public String toggleComplete(@RequestParam("taskId") int taskId) {
        toDoService.toggleTaskCompletion(taskId);

        return "redirect:/";
    }

    @PostMapping("/todos/update")
    public String updateTask(@RequestParam("taskId") int taskId,
                             @RequestParam("newDescription") String newDescription) {
        toDoService.toggleTaskCompletion(taskId);
        toDoService.UpdateTask(taskId, newDescription);

        return "redirect:/";
    }

    @PostMapping("/todos/delete")
    public String deleteTask(@RequestParam("taskId") int taskId) {
        toDoService.deleteTask(taskId);

        return "redirect:/";
    }


}
