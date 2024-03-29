package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.services.LibraryService;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final LibraryService libraryService;

    @GetMapping(value = "/api/books")
    public Page<Book> findAll(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return libraryService.findPage(PageRequest.of(page - 1, size));
    }

    @GetMapping(value = "/api/book")
    public ResponseEntity<Book> findById(@RequestParam("id") Long id) {
        return new ResponseEntity<>(libraryService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/api/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Book> saveAndUpdate(@RequestBody Book book) {
        return new ResponseEntity<>(libraryService.addBook(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/books/")
    public ResponseEntity<String> deleteById(@RequestParam("id") Long id) {
        libraryService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
