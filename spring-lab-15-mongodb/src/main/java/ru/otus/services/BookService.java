package ru.otus.services;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import java.util.List;


public interface BookService {
    List<String> getAllBooks();
    String getBookById(String id);
    Book addBook(String title, String authorId, String genreId );
    void deleteBook(String id);
    void updateBookNameById(String id, String name);
    List<String> getAllAuthors();
    Author addAuthor(String name);
    void deleteAuthor(String authorId);
    List<String> getAllGenres();
    Genre addGenre(String genreName);
    void deleteGenre(String genreId);
    Comment addComment(String bookId, String text);
    List<Comment> findCommentsByBookId(String bookId);
    void deleteCommentById(String commentId);
    void updateCommentById(String commentId, String text);
}
