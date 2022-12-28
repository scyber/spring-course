package ru.otus.spring.events;
;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.FluxSink;

import java.util.function.Consumer;

@Component
public class BlogCreatedEventPublisher implements ApplicationListener<BlogCreationEvent>, Consumer<FluxSink<BlogCreationEvent>> {

    private FluxSink<BlogCreationEvent> sink;

    @Override
    public void accept(FluxSink<BlogCreationEvent> sink) {
        this.sink = sink;
        sink.onDispose(() -> this.sink = null);
    }

    @Override
    public void onApplicationEvent(BlogCreationEvent event) {
        FluxSink<BlogCreationEvent> sink = this.sink;
        if(sink != null){
            sink.next(event);
        }
    }
}
