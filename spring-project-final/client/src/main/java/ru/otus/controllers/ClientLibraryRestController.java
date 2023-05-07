package ru.otus.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
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

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/books")
    Page<Book> getBooks(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return libraryClientService.getPagebleBooks(page,size);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/books/{bookId}")
    Optional<Book> getBookById(@PathVariable("bookId") String bookId){
        return libraryClientService.getBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client/api/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Book saveAndUpdateBook(@RequestBody Book book){
        return libraryClientService.saveAndUpdateBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/client/api/books/{bookId}")
    void deleteBookById(@PathVariable("bookId") String bookId){
        libraryClientService.deleteBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/client/api/books/{bookId}")
    void updateBookTitleById(@PathVariable("bookId") String bookId, @RequestBody String title){
        libraryClientService.updateBookTitleById(bookId,title);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/authors")
    public List<Author> getAuthors() {
        return libraryClientService.getAllAuthors();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/genres")
    public List<Genre> getGenres(){
        return libraryClientService.getAllGenres();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/comments")
    public List<Comment> getComments(){
        return libraryClientService.getAllComments();
    }


}
