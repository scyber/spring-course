package ru.otus.repository;

import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre domain);
    List<Genre> findAll();
    Optional<Genre> findById(long id);
    List<Genre> findByName(String name);
    void delete(long id);
}
