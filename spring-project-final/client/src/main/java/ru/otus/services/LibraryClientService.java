package ru.otus.services;

import org.springframework.data.domain.Page;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;


public interface LibraryClientService {

    Page<Book> getPagebleBooks(Integer page, Integer size);

    Optional<Book> getBookById(String id);

    Book saveAndUpdateBook(Book book);

    void deleteBookById(String id);

    Book updateBookTitleById(String bookId, String title);

    List<Author> getAllAuthors();

    List<Genre> getAllGenres();

    List<Comment> getAllComments();

}
