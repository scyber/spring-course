package ru.otus.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.otus.clients.LibraryClient;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class LibraryClientServiceImpl implements LibraryClientService {

    private final LibraryClient libraryClient;

    @HystrixCommand(fallbackMethod = "failGetAllBooks")
    @Override
    public Page<Book> getPagebleBooks(Integer page, Integer size) {
        return libraryClient.getBooks(PageRequest.of(page, size));
    }

    @HystrixCommand
    @Override
    public Optional<Book> getBookById(String id) {
        return libraryClient.getBookById(id);
    }

    @HystrixCommand
    @Override
    public Book saveAndUpdateBook(Book book) {
        return libraryClient.saveAndUpdateBook(book);
    }

    @HystrixCommand
    @Override
    public void deleteBookById(String id) {
        libraryClient.deleteBookById(id);
    }

    @HystrixCommand()
    @Override
    public Book updateBookTitleById(String bookId, String title) {
        return libraryClient.updateBookTitleById(bookId, title);
    }

    @HystrixCommand
    @Override
    public Genre saveAndUpdateGenre(Genre genre) {
        return libraryClient.saveAndUpdateGenre(genre);
    }

    @HystrixCommand
    @Override
    public Genre getGenreById(String id) {
        return libraryClient.getGenreById(id);
    }

    @HystrixCommand
    @Override
    public void deleteGenreById(String id) {
        libraryClient.deleteGenreById(id);
    }

    @HystrixCommand
    @Override
    public Author saveAndUpdateAuthor(Author author) {
        return libraryClient.saveAndUpdateAuthor(author);
    }

    @HystrixCommand
    @Override
    public Author getAuthorById(String id) {
        return libraryClient.getAuthorById(id);
    }

    @HystrixCommand
    @Override
    public void deleteAuthorById(String id) {
        libraryClient.deleteAuthorById(id);
    }

    @Override
    public void deleteCommentById(String commentId) {
        libraryClient.deleteComment(commentId);
    }

    @Override
    public Comment addCommentByBookId(String bookId, Comment comment) {
        return libraryClient.addComment(bookId, comment);
    }

    @HystrixCommand(fallbackMethod = "failGetAllAuthors")
    @Override
    public List<Author> getAllAuthors() {
        return libraryClient.getAllAuthors();
    }

    @HystrixCommand(fallbackMethod = "failGetAllGenres")
    @Override
    public List<Genre> getAllGenres() {
        return libraryClient.getAllGenres();
    }

    @HystrixCommand
    @Override
    public List<Comment> getAllComments() {
        return libraryClient.getAllComments();
    }

    private Page<Book> failGetAllBooks(Integer page, Integer size) {
        var emptyList = new ArrayList<Book>();
        var emptyBookPage = new PageImpl<>(emptyList);
        return emptyBookPage;
    }

    private List<Author> failGetAllAuthors() {
        var emptyAuthorsList = new ArrayList<Author>();
        return emptyAuthorsList;
    }

    private List<Genre> failGetAllGenres() {
        var emptyEngere = new ArrayList<Genre>();
        return emptyEngere;
    }
}
