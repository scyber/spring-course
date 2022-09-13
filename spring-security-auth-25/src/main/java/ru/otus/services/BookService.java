package ru.otus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import java.util.List;


public interface BookService {
    Page<Book> findPage(Pageable pageable);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book addBook(String title, Long authorId, Long genreId );
    void deleteBook(Long id);
    void updateBookNameById(Long id, String name);
    List<Author> getAllAuthors();
    Author addAuthor(String name);
    void deleteAuthor(Long authorId);
    List<Genre> getAllGenres();
    Genre addGenre(String genreName);
    void deleteGenre(Long genreId);
    Comment addComment(Long bookId, String text);
    List<Comment> findCommentsByBookId(Long bookId);
    void deleteCommentById(Long commentId);
    void updateCommentById(Long commentId, String text);

    void addAuthorForBook(Long bookId, Long authorId);

    void deleteAuthorFromBook(Long bookId, Long authorId);

    void addGenreForBook(Long bookId, Long genreId);

    void deleteGenreFromBook(Long bookId, Long genreId);

}
