package ru.otus.repositories;

import reactor.core.publisher.Flux;
import ru.otus.domain.Author;

public interface AuthorRepositoryCustom {
    Flux<Author> findByName(String name);
}
