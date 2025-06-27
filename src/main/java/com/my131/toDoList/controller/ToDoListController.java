package com.my131.toDoList.controller;

import com.my131.toDoList.service.ToDoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String addTask(@RequestParam("task") String task) {  //html에서 post로 task인 값 받아서 String task에 저장
        toDoService.addTask(task);

        return "redirect:/";
    }

    @PostMapping("/todos/delete")
    public String deleteTask(@RequestParam("taskId") int taskId) {
        System.out.println("delete");
        toDoService.deleteTask(taskId);

        return "redirect:/";
    }
}
