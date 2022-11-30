package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Author;
import ru.otus.services.BookService;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AuthorsRestController {

    private final BookService bookService;

    @GetMapping("/api/authors")
    public List<Author> findAll() {
        return bookService.getAllAuthors();
    }

}
