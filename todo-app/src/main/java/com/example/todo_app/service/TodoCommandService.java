package com.example.todo_app.service;

import com.example.todo_app.data.Todo;
import com.example.todo_app.dto.CreateTodoCommand;
import com.example.todo_app.dto.UpdateTodoCommand;
import com.example.todo_app.event.TodoCompletedEvent;
import com.example.todo_app.event.TodoCreatedEvent;
import com.example.todo_app.event.TodoDeletedEvent;
import com.example.todo_app.event.TodoUpdatedEvent;
import com.example.todo_app.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoCommandService {

    private final TodoRepository todoRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public Todo createTodo(CreateTodoCommand command) {
        Todo todo = new Todo(command.getTitle());
        Todo savedTodo = todoRepository.save(todo);
        
        eventPublisher.publishEvent(new TodoCreatedEvent(this, savedTodo.getId(), savedTodo.getTitle()));
        
        return savedTodo;
    }

    @Transactional
    public void completeTodo(Long id) {
        todoRepository.findById(id).ifPresent(todo -> {
            todo.markCompleted();
            todoRepository.save(todo);

            eventPublisher.publishEvent(new TodoCompletedEvent(this, todo.getId()));
        });
    }

    @Transactional
    public void deleteTodo(Long id) {
        todoRepository.findById(id).ifPresent(todo -> {
            todoRepository.delete(todo);
            

            eventPublisher.publishEvent(new TodoDeletedEvent(this, id));
        });
    }

    @Transactional
    public void updateTodo(Long id, UpdateTodoCommand command) {
        todoRepository.findById(id).ifPresent(todo -> {
            String oldTitle = todo.getTitle();
            todo.setTitle(command.getTitle());
            todoRepository.save(todo);

            eventPublisher.publishEvent(new TodoUpdatedEvent(this, todo.getId(), oldTitle, command.getTitle()));
        });
    }

    @Transactional(readOnly = true)
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
