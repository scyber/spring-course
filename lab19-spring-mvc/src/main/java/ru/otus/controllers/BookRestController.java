package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
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
    public Page<Book> findAll(Integer page, Integer size) {
        return libraryService.findPage(page,size);
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
