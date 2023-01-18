package ru.otus.spring.domain;

import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import ru.otus.spring.repository.BlogRepository;
import ru.otus.spring.domain.Blog;
import java.util.UUID;

@Component
@Log4j2
class SampleDataInitialiser implements ApplicationListener<ApplicationReadyEvent> {

	private BlogRepository blogRepository;

	SampleDataInitialiser(BlogRepository blogRepository) {
		this.blogRepository = blogRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		blogRepository.deleteAll().thenMany(reactor.core.publisher.Flux.just("A", "B", "C", "D")
				.map(name -> new Blog(UUID.randomUUID().toString(), name + "@gmail.com")).flatMap(blogRepository::save))
				.thenMany(blogRepository.findAll()).subscribe(blog -> log.info("saving " + blog.toString()));
	}
}
