package ru.otus.spring.events;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;
import reactor.core.publisher.FluxSink;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;

@Component
public class BlogCreatedEventPublisher
		implements ApplicationListener<BlogCreationEvent>, Consumer<FluxSink<BlogCreationEvent>> {

	private final Executor executor;
	private final BlockingQueue<BlogCreationEvent> queue = new LinkedBlockingQueue<>();

	BlogCreatedEventPublisher(Executor executor) {
		this.executor = executor;
	}

	@Override
	public void accept(FluxSink<BlogCreationEvent> sink) {
		this.executor.execute(() -> {
			while (!queue.isEmpty())
				try {

					BlogCreationEvent event = queue.take();
					sink.next(event);
				} catch (Exception e) {
					ReflectionUtils.rethrowRuntimeException(e);
				}
		});
	}

	@Override
	public void onApplicationEvent(BlogCreationEvent event) {
		this.queue.offer(event);
	}
}
