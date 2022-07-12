package ru.otus.dao;

import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    long save(Book domain);

    void delete(long id);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void updateNameById(long id, String name);
}
