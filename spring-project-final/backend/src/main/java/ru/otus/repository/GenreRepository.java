package ru.otus.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Genre;

public interface GenreRepository extends ReactiveCrudRepository<Genre,String>,GenreRepositoryCustom {

    Flux<Genre> findAll();

    Mono<Genre> findById(String id);

    Mono<Void> deleteById(String id);
}
