package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepository;
import ru.otus.services.BookService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreRestController {

    private final BookService bookService;

    @GetMapping("/api/genres")
    public List<Genre> findAll() {
        return bookService.getAllGenres();
    }


}
