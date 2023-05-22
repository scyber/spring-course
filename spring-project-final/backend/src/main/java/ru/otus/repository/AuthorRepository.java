package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;


public interface AuthorRepository extends ReactiveMongoRepository<Author,String>{

    Mono<Author> save(Author domain);

    Flux<Author> findAll();

    Mono<Author> findById(String id);

    Mono<Void> deleteById( String id);

    Flux<Author> findByName(String name);
}
