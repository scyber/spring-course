package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Genre;
import ru.otus.services.LibraryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreRestController {

    private final LibraryService libraryService;

    @GetMapping("/api/genres")
    public List<Genre> findAll() {
        return libraryService.getAllGenres();
    }


}
