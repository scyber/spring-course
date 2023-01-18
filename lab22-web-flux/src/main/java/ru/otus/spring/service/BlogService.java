package ru.otus.spring.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Blog;
import ru.otus.spring.events.BlogCreationEvent;
import ru.otus.spring.repository.BlogRepository;

@Log4j2
@Service
public class BlogService {
	private final BlogRepository blogRepository;
	private final ApplicationEventPublisher applicationEventPublisher;

	public BlogService(BlogRepository blogRepository, ApplicationEventPublisher applicationEventPublisher) {
		this.blogRepository = blogRepository;
		this.applicationEventPublisher = applicationEventPublisher;
	}
	
	public Mono<Page<Blog>> findPage(PageRequest request) {
		
		return this.blogRepository.findAllBy(request)
				.collectList()
				.zipWith(this.blogRepository.count())
				.map(t -> new PageImpl<>(t.getT1(),request,t.getT2()));
				
	}

	public Flux<Blog> all() {
		return this.blogRepository.findAll();
	}

	public Mono<Blog> get(String id) {
		return this.blogRepository.findById(id);
	}

	public Mono<Blog> update(String id, String email) {
		return this.blogRepository.findById(id).map(b -> new Blog(b.getId(), email)).flatMap(this.blogRepository::save);
	}

	public Mono<Blog> delete(String id) {
		return this.blogRepository.findById(id).flatMap(p -> this.blogRepository.deleteById(p.getId()).thenReturn(p));
	}

	public Mono<Blog> create(String email) {
		return this.blogRepository.save(new Blog(null, email)).doOnSuccess(entity -> {
			log.info("entity created " + entity);
			this.applicationEventPublisher.publishEvent(new BlogCreationEvent(entity));
		});

	}

}
