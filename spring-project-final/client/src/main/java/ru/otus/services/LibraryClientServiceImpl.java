package ru.otus.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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

    private final LibraryServiceClient libraryServiceClient;

    @HystrixCommand(fallbackMethod = "failGetAllBooks")
    @Override
    public Page<Book> getPagebleBooks(Integer page, Integer size) {
        return libraryServiceClient.getBooks(PageRequest.of(page, size));
    }

    @HystrixCommand
    @Override
    public Optional<Book> getBookById(String id) {
        return libraryServiceClient.getBookById(id);
    }

    @HystrixCommand
    @Override
    public Book saveAndUpdateBook(Book book) {
        return libraryServiceClient.saveAndUpdateBook(book);
    }

    @HystrixCommand
    @Override
    public void deleteBookById(String id) {
        libraryServiceClient.deleteBookById(id);
    }

    @HystrixCommand()
    @Override
    public Book updateBookTitleById(String bookId, String title) {
        return libraryServiceClient.updateBookTitleById(bookId, title);
    }

    @HystrixCommand
    @Override
    public Genre saveAndUpdateGenre(Genre genre) {
        return libraryServiceClient.saveAndUpdateGenre(genre);
    }

    @HystrixCommand
    @Override
    public Genre getGenreById(String id) {
        return libraryServiceClient.getGenreById(id);
    }

    @HystrixCommand
    @Override
    public void deleteGenreById(String id) {
        libraryServiceClient.deleteGenreById(id);
    }

    @HystrixCommand
    @Override
    public Author saveAndUpdateAuthor(Author author) {
        return libraryServiceClient.saveAndUpdateAuthor(author);
    }

    @HystrixCommand
    @Override
    public Author getAuthorById(String id) {
        return libraryServiceClient.getAuthorById(id);
    }

    @HystrixCommand
    @Override
    public void deleteAuthorById(String id) {
        libraryServiceClient.deleteAuthorById(id);
    }

    @Override
    public void deleteCommentById(String commentId) {
        libraryServiceClient.deleteComment(commentId);
    }

    @Override
    public Comment addCommentByBookId(String bookId, Comment comment) {
        return libraryServiceClient.addComment(bookId, comment);
    }

    @HystrixCommand(fallbackMethod = "failGetAllAuthors")
    @Override
    public List<Author> getAllAuthors() {
        return libraryServiceClient.getAllAuthors();
    }

    @HystrixCommand(fallbackMethod = "failGetAllGenres")
    @Override
    public List<Genre> getAllGenres() {
        return libraryServiceClient.getAllGenres();
    }

    @HystrixCommand
    @Override
    public List<Comment> getAllComments() {
        return libraryServiceClient.getAllComments();
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
