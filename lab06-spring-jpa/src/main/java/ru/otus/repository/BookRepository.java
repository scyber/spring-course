package ru.otus.repository;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(long bookId);
    List<Book> findAll();
    void deleteById(long bookId);
    List<Book> findByTitle(String title);
    List<Comment> getComments(long bookId);
    void updateBookTitleById(long bookId, String title);
}
