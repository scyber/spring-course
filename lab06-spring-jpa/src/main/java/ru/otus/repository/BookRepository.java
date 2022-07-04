package ru.otus.repository;

import ru.otus.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book save(Book book);
    Optional<Book> findById(long id);
    List<Book> findAll();
    List<Book> findByName(String name);

    void updateNameById(long id, String name);
    void deleteById(long id);

    void updateAuthorById(long bookId, long authorId);
    void updateGenreById(long bookId, long genreId);

}
