package ru.otus.spring.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.spring.domain.Blog;
import ru.otus.spring.events.BlogCreationEvent;
import ru.otus.spring.repository.BlogRepository;

@Log4j2
@Service
@RequiredArgsConstructor
public class BlogService {
    private final BlogRepository blogRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public Flux<Blog> all(){
        return this.blogRepository.findAll();
    }

    public Mono<Blog> get(String id){
        return this.blogRepository.findById(id);
    }

    public Mono<Blog> update(String id, String email){
        return this.blogRepository.findById(id)
                .map(b -> new Blog(b.getId(), email))
                .flatMap(this.blogRepository::save);
    }

    public Mono<Blog> delete(String id){
        return this.blogRepository.findById(id)
                .flatMap(p -> this.blogRepository.deleteById(p.getId()).thenReturn(p));
    }

    public Mono<Blog> create(String email){
        return this.blogRepository.save(new Blog(null, email))
                .doOnSuccess(entity -> {
                    log.info("entity created " + entity);
                    this.applicationEventPublisher.publishEvent(new BlogCreationEvent(entity));
                });

    }

}
