package ru.otus.repository;

import reactor.core.publisher.Flux;
import ru.otus.domain.Genre;

public interface GenreRepositoryCustom {
    Flux<Genre> findByName(String name);
}
