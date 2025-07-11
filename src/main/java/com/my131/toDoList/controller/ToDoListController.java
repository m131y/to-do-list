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

    @GetMapping("/todos")
    public String listTodos(@RequestParam(defaultValue = "created") String sort, Model model) {
        System.out.println("정렬");
        model.addAttribute("todos", toDoService.getAllSorted(sort));
        model.addAttribute("sort", sort);  // 선택된 상태 유지용
        return "todolist";
    }

    @PostMapping("/todos")
    public String addTask(@RequestParam("task") String task, String color, LocalDate taskStartDate, LocalDate taskEndDate) {  //html에서 post로 task인 값 받아서 String task에 저장
        System.out.println(task+color+taskStartDate+taskEndDate);
        toDoService.addTask(task, color, taskStartDate, taskEndDate);


        return "redirect:/";
    }

    @PostMapping("/todos/toggle")
    public String toggleComplete(@RequestParam("taskId") int taskId,
                                 @RequestParam("sort") String sort) {
        toDoService.toggleTaskCompletion(taskId);

        return "redirect:/todos?sort=" + sort;
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
