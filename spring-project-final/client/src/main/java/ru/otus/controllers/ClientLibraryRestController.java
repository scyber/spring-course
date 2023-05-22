package ru.otus.controllers;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.services.LibraryClientService;
import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ClientLibraryRestController {

    private final LibraryClientService libraryClientService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientLibraryRestController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/books")
    Page<Book> getBooks(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return libraryClientService.getPagebleBooks(page, size);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/books/{bookId}")
    Optional<Book> getBookById(@PathVariable("bookId") String bookId) {
        return libraryClientService.getBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client/api/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Book saveAndUpdateBook(@RequestBody Book book) {
        return libraryClientService.saveAndUpdateBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/client/api/books/{bookId}")
    void deleteBookById(@PathVariable("bookId") String bookId) {
        libraryClientService.deleteBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client/api/books/{bookId}")
    void updateBookTitleById(@PathVariable("bookId") String bookId, @RequestBody String title) {
        libraryClientService.updateBookTitleById(bookId, title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/authors")
    public List<Author> getAuthors() {
        return libraryClientService.getAllAuthors();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/genres")
    public List<Genre> getGenres() {
        return libraryClientService.getAllGenres();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client/api/genres", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Genre saveAndUpdateGenre(@RequestBody Genre genre) {
        return libraryClientService.saveAndUpdateGenre(genre);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/genres/{id}")
    public Genre getGenreById(@PathVariable("id") String id) {
        return libraryClientService.getGenreById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/client/api/genres/{genreId}")
    public void deleteGenre(@PathVariable("genreId") String genreId) {
        libraryClientService.deleteGenreById(genreId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/authors/{authorId}")
    public Author getAuthorById(@PathVariable("authorId") String authorId) {
        return libraryClientService.getAuthorById(authorId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client/api/authors", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Author addAuthor(@RequestBody Author author) {
        return libraryClientService.saveAndUpdateAuthor(author);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/client/api/authors/{authorId}")
    public void deleteAuthor(@PathVariable("authorId") String authorId) {
        libraryClientService.deleteAuthorById(authorId);
    }


}
