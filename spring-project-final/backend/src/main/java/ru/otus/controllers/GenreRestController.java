package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Genre;
import ru.otus.repositories.GenreRepository;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class GenreRestController {

    private final GenreRepository genreRepository;

    @GetMapping("/api/genres")
    public Flux<Genre> findAll() {
        return this.genreRepository.findAll();
    }
    
    @PostMapping("/api/genres")
    public Mono<Genre> save(@RequestBody Genre genre){
    	return this.genreRepository.save(genre);
    }

    @DeleteMapping("/api/genres/{id}")
    public Mono<Void> delete(@PathVariable("id") String id){
    	return this.genreRepository.deleteById(id);
    }

}
