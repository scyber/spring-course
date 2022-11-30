package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.services.BookService;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookService bookService;

    @GetMapping(value = "/api/books")
    public Page<Book> findAll(Integer page, Integer size) {
        return bookService.findPage(page,size);
    }

    @GetMapping(value = "/api/books/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return new ResponseEntity<>(bookService.getBookById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/api/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Book> saveAndUpdate(@RequestBody Book book) {
        return new ResponseEntity<>(bookService.addBook(book), HttpStatus.OK);
    }

    @DeleteMapping(value = "/api/books/")
    public ResponseEntity<String> deleteById(@RequestParam("id") Long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
