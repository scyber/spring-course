package ru.otus.services;

import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.model.Genre;
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
