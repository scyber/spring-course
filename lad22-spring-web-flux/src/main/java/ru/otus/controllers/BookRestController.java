package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.repository.BookRepository;

@RestController
@RequiredArgsConstructor
public class BookRestController {

    private final BookRepository bookRepository;

    @GetMapping(value = "/api/books")
    public Mono<Page<Book>> findAll(PageRequest request) {
        return this.bookRepository.findAllBy(request)
        		.collectList()
        		.zipWith(this.bookRepository.count())
        		.map(t -> new PageImpl<>(t.getT1(), request,t.getT2()));
    }

    @GetMapping(value = "/api/book/{id}")
    public Mono<Book> findById(@PathVariable("id") String id) {
        return this.bookRepository.findById(id);
    }

    @PostMapping(value = "/api/book", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Book> saveAndUpdate(@RequestBody Book book) {
        return this.bookRepository.save(book);
    }

    @DeleteMapping(value = "/api/book/")
    public Mono<Void> deleteById(@RequestParam("id") String id) {
        return this.bookRepository.deleteById(id);
        
    }
}
