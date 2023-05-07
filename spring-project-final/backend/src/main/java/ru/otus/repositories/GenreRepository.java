package ru.otus.repositories;

import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre,String> {

    Flux<Genre> findAll();

    Mono<Genre> findById(@Param("id") String id);

    Flux<Genre> findByName(@Param("name") String name);

    Mono<Void> deleteById(@Param("id") String id);
}
