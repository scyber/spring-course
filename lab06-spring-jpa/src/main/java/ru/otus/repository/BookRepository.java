package ru.otus.repository;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book domain);
    Optional<Book> findById(long id);
    List<Book> findAll();
    void delete(long id);
    List<Book> findByName(String name);
    List<Comment> getComments(long id);
    void updateNameById( long id, String name);
}
