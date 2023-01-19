package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Author;
import ru.otus.services.LibraryService;


@RequiredArgsConstructor
@RestController
public class AuthorsRestController {

    private final LibraryService libraryService;

    @GetMapping("/api/authors")
    public Flux<Author> findAll() {
        return libraryService.getAllAuthors();
    }

}
