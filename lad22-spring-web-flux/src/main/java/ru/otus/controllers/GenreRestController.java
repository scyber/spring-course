package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Genre;
import ru.otus.services.LibraryService;

@RestController
@RequiredArgsConstructor
public class GenreRestController {

    private final LibraryService libraryService;

    @GetMapping("/api/genres")
    public Flux<Genre> findAll() {
        return libraryService.getAllGenres();
    }


}
