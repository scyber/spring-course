package ru.otus.controllers;


import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import ru.otus.domain.Author;
import ru.otus.repositories.AuthorRepository;



@RestController
@CrossOrigin(origins = "*")
public class AuthorsRestController {

    private final AuthorRepository authorsRepository;
    
    public AuthorsRestController(AuthorRepository authorsRepository) {
		this.authorsRepository = authorsRepository;
	}


	@GetMapping("/api/authors")
    public Flux<Author> findAll() {
        return this.authorsRepository.findAll();
    }

	@PostMapping(value = "/api/authors", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Author> save(@RequestBody Author author) {
        return this.authorsRepository.save(author);
    }

	@DeleteMapping(value = "/api/authors/{id}")
    public Mono<Void> delete(@PathVariable("id") String id) {
        return this.authorsRepository.deleteById(id);
    }

    @GetMapping(value = "/api/author/{id}")
    public Mono<Author> findById(@PathVariable("id") String id){
        return authorsRepository.findById(id);
    }

    @GetMapping(value = "/api/author")
    public Flux<Author> findByName(@RequestParam("name") String name){
        return authorsRepository.findByName(name);
    }

}
