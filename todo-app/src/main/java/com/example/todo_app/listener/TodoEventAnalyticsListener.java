package com.example.todo_app.listener;

import com.example.todo_app.event.TodoCompletedEvent;
import com.example.todo_app.event.TodoCreatedEvent;
import com.example.todo_app.event.TodoDeletedEvent;
import com.example.todo_app.event.TodoUpdatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TodoEventAnalyticsListener {

    private static final Logger log = LoggerFactory.getLogger(TodoEventAnalyticsListener.class);

    @Async
    @EventListener
    public void handle(TodoCreatedEvent event) {
        log.info("ASYNC EVENT: Todo created with ID {} and title '{}'", event.getTodoId(), event.getTitle());
 
    }

    @Async
    @EventListener
    public void handle(TodoCompletedEvent event) {
        log.info("ASYNC EVENT: Todo completed with ID {}", event.getTodoId());
    }

    @Async
    @EventListener
    public void handle(TodoUpdatedEvent event) {
        log.info("ASYNC EVENT: Todo updated with ID {} — '{}' → '{}'", event.getTodoId(), event.getOldTitle(), event.getNewTitle());
    }

    @Async
    @EventListener
    public void handle(TodoDeletedEvent event) {
        log.info("ASYNC EVENT: Todo deleted with ID {}", event.getTodoId());
    }
}

