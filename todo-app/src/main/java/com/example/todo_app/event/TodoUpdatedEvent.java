package com.example.todo_app.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class TodoUpdatedEvent extends ApplicationEvent {
    private final Long todoId;
    private final String oldTitle;
    private final String newTitle;

    public TodoUpdatedEvent(Object source, Long todoId, String oldTitle, String newTitle) {
        super(source);
        this.todoId = todoId;
        this.oldTitle = oldTitle;
        this.newTitle = newTitle;
    }
}
