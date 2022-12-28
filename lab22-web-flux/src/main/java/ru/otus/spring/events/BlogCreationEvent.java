package ru.otus.spring.events;

import org.springframework.context.ApplicationEvent;
import ru.otus.spring.domain.Blog;

public class BlogCreationEvent extends ApplicationEvent {
    public BlogCreationEvent(Blog source) {
        super(source);
    }
}
