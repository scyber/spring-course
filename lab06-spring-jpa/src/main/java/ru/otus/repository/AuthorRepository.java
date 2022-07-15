package ru.otus.repository;

import ru.otus.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author domain);
    List<Author> findAll();
    Optional<Author> findById(long id);
    List<Author> findByName(String name);
    void delete(long id);
    void updateAuthorNameById(long id, String name);
}
