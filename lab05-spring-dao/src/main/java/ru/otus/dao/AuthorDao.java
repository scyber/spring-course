package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    long save(Author domain);

    void delete(long authorId);

    Optional<Author> findById(long authorId);

    List<Author> findAll();
}
