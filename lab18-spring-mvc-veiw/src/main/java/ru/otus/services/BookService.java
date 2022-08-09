package ru.otus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import java.util.List;


public interface BookService {
    Page<Book> findPage(Pageable pageable);
    List<String> getAllBooks();
    String getBookById(Long id);
    Book addBook(String title, Long authorId, Long genreId );
    void deleteBook(Long id);
    void updateBookNameById(Long id, String name);
    List<String> getAllAuthors();
    Author addAuthor(String name);
    void deleteAuthor(Long authorId);
    List<String> getAllGenres();
    Genre addGenre(String genreName);
    void deleteGenre(Long genreId);
    Comment addComment(Long bookId, String text);
    List<Comment> findCommentsByBookId(Long bookId);
    void deleteCommentById(Long commentId);
    void updateCommentById(Long commentId, String text);
}
