package ru.otus.services;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<String> getAllBooks();
    String getBookById(long id);
    Book addBook(String title, long authorId, long genreId );
    void deleteBook(long id);
    void updateBookNameById(long id, String name);
    List<String> getAllAuthors();
    Author addAuthor(String name);
    void deleteAuthor(long authorId);
    List<String> getAllGenres();
    Genre addGenre(String genreName);
    void deleteGenre(long genreId);
    Comment addComment(long bookId, String text);
    List<Comment> findCommentsByBookId(long bookId);
    void deleteCommentById(long commentId);
    void updateCommentById(long commentId, String text);
}
