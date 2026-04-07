package com.example.todo_app.controller;

import com.example.todo_app.dto.CreateTodoCommand;
import com.example.todo_app.dto.UpdateTodoCommand;
import com.example.todo_app.service.TodoCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class TodoWebController {

    private final TodoCommandService todoCommandService;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("todos", todoCommandService.getAllTodos());
        return "index";
    }

    @PostMapping("/add")
    public String addTodo(@RequestParam("title") String title) {
        CreateTodoCommand command = new CreateTodoCommand();
        command.setTitle(title);
        todoCommandService.createTodo(command);
        return "redirect:/";
    }

    @PostMapping("/edit/{id}")
    public String editTodo(@PathVariable Long id, @RequestParam("title") String title) {
        UpdateTodoCommand command = new UpdateTodoCommand();
        command.setTitle(title);
        todoCommandService.updateTodo(id, command);
        return "redirect:/";
    }

    @PostMapping("/complete/{id}")
    public String completeTodo(@PathVariable Long id) {
        todoCommandService.completeTodo(id);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable Long id) {
        todoCommandService.deleteTodo(id);
        return "redirect:/";
    }
}

