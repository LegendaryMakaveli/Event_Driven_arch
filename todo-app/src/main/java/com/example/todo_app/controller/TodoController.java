package com.example.todo_app.controller;

import com.example.todo_app.data.Todo;
import com.example.todo_app.dto.CreateTodoCommand;
import com.example.todo_app.dto.UpdateTodoCommand;
import com.example.todo_app.service.TodoCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoCommandService todoCommandService;

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoCommandService.getAllTodos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody CreateTodoCommand command) {
        return todoCommandService.createTodo(command);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTodo(@PathVariable Long id, @RequestBody UpdateTodoCommand command) {
        todoCommandService.updateTodo(id, command);
    }

    @PutMapping("/{id}/complete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void completeTodo(@PathVariable Long id) {
        todoCommandService.completeTodo(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(@PathVariable Long id) {
        todoCommandService.deleteTodo(id);
    }
}

