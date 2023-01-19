package ru.otus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import java.util.List;


public interface LibraryService {
    Mono<Page<Book>> findPage(PageRequest request);
    Flux<Book> getAllBooks();
    Mono<Book> getBookById(String id);
    Mono<Book> addBook(Book book);
    Mono<Void> deleteBook(String id);
    Mono<Void> updateBookNameById(String id, String name);
    Flux<Author> getAllAuthors();
    Mono<Author> addAuthor(String name);
    Mono<Void> deleteAuthor(String authorId);
    Flux<Genre> getAllGenres();
    Mono<Genre> addGenre(String genreName);
    Mono<Void> deleteGenre(String genreId);

    Flux<Comment> getAllComments();
    Mono<Comment> addComment(Book book, String text);
    Flux<Comment> findCommentsByBookId(String bookId);
    Mono<Void> deleteCommentById(String commentId);
    Mono<Void> updateCommentById(String commentId, String text);
    Mono<Book> addAuthorForBook(String bookId, String authorId);

    Mono<Book> deleteAuthorFromBook(String bookId, String authorId);

    Mono<Book> addGenreForBook(String bookId, String genreId);

    Mono<Book> deleteGenreFromBook(String bookId, String genreId);

}
