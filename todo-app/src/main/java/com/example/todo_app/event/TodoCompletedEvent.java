package com.example.todo_app.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TodoCompletedEvent extends ApplicationEvent {
    private final Long todoId;

    public TodoCompletedEvent(Object source, Long todoId) {
        super(source);
        this.todoId = todoId;
    }
}
