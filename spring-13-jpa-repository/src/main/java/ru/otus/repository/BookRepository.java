package ru.otus.repository;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book domain);
    Optional<Book> findById(long bookId);
    List<Book> findAll();
    void deleteById(long bookId);
    List<Book> findByName(String name);
    List<Comment> getComments(long bookId);
    void updateBookNameById(long bookId, String name);
}
