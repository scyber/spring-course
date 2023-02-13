package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Genre;
import ru.otus.repository.GenreRepository;

@RestController
@RequiredArgsConstructor
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
