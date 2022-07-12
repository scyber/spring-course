package ru.otus.dao;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {
    long save(Author domain);
    void delete(long id);
    Optional<Author> findById(Long id);
    List<Author> findAll();
}
