package ru.otus.repository;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;


public interface AuthorRepository extends ReactiveMongoRepository<Author,String> {

    Mono<Author> save(Author domain);

    Flux<Author> findAll();

    Mono<Author> findById(Long id);

    //@Query("select a from Author a where a.name = :name")
    Flux<Author> findByname(String name);

    Mono<Void> deleteById(@Param("id") String id);

}
