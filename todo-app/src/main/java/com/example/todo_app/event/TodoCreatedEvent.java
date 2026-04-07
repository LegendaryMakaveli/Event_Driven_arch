package com.example.todo_app.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TodoCreatedEvent extends ApplicationEvent {
    private final Long todoId;
    private final String title;

    public TodoCreatedEvent(Object source, Long todoId, String title) {
        super(source);
        this.todoId = todoId;
        this.title = title;
    }
}
