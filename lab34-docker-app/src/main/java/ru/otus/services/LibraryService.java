package ru.otus.services;

import org.springframework.data.domain.Page;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import java.util.List;


public interface LibraryService {
    Page<Book> findPage(Integer page, Integer size);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book addBook(Book book);
    void deleteBook(Long id);
    void updateBookNameById(Long id, String name);
    List<Author> getAllAuthors();
    Author addAuthor(String name);
    void deleteAuthor(Long authorId);
    List<Genre> getAllGenres();
    Genre addGenre(String genreName);
    void deleteGenre(Long genreId);

    List<Comment> getAllComments();
    Comment addComment(Book book, String text);
    List<Comment> findCommentsByBookId(Long bookId);
    void deleteCommentById(Long commentId);
    void updateCommentById(Long commentId, String text);
    void addAuthorForBook(Long bookId, Long authorId);

    void deleteAuthorFromBook(Long bookId, Long authorId);

    void addGenreForBook(Long bookId, Long genreId);

    void deleteGenreFromBook(Long bookId, Long genreId);

}
