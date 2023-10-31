package ru.otus.controllers;


import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.services.LibraryClientService;

import java.util.Optional;


@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ClientBookRestController {

    private final LibraryClientService libraryClientService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientBookRestController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/api/client/books")
    Page<Book> getBooks(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return libraryClientService.getPagebleBooks(page, size);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/client/books/{bookId}")
    Optional<Book> getBookById(@PathVariable("bookId") String bookId) {
        return libraryClientService.getBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/client/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Book saveAndUpdateBook(@RequestBody Book book) {
        return libraryClientService.saveAndUpdateBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/client/books/{bookId}")
    void deleteBookById(@PathVariable("bookId") String bookId) {
        libraryClientService.deleteBookById(bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/client/books/{bookId}")
    void updateBookTitleById(@PathVariable("bookId") String bookId, @RequestBody String title) {
        libraryClientService.updateBookTitleById(bookId, title);
    }

}
