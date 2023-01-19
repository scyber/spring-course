package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.services.LibraryService;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final LibraryService libraryService;

    @GetMapping(value = "/api/books")
    public Mono<Page<Book>> findAll(PageRequest request) {
        return libraryService.findPage(request);
    }

    @GetMapping(value = "/api/book")
    public Mono<Book> findById(@RequestParam("id") String id) {
        return libraryService.getBookById(id);
    }

    @PostMapping(value = "/api/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Book> saveAndUpdate(@RequestBody Book book) {
        return libraryService.addBook(book);
    }

    @DeleteMapping(value = "/api/books/")
    public Mono<Void> deleteById(@RequestParam("id") String id) {
        return libraryService.deleteBook(id);
        
    }
}
