package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
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
    
    @PostMapping(value = "/api/genres", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Mono<Genre> save(@RequestBody Genre genre){
    	return this.genreRepository.save(genre);
    }

    @DeleteMapping("/api/genres/{id}")
    public Mono<Void> delete(@PathVariable("id") String id){
    	return this.genreRepository.deleteById(id);
    }

    @GetMapping(value = "/api/genre/{id}")
    public Mono<Genre> getById(@PathVariable("id") String id){
        return this.genreRepository.findById(id);
    }

    @GetMapping(value = "/api/genre")
    public Flux<Genre> findByName(@RequestParam("name") String name){
        return this.genreRepository.findByName(name);
    }

}
