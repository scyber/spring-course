package ru.otus.dao;

import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {
    long save(Genre genre);

    void delete(long id);

    List<Genre> findAll();

    Optional<Genre> findById(long id);
}
