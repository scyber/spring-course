package ru.otus.controllers;


import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepository;



@RestController
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

}
