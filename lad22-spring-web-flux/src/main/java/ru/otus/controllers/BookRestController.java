package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.reactivestreams.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.dto.BookDto;
import ru.otus.repository.BookRepository;
import ru.otus.services.LibraryService;

@RestController
@RequiredArgsConstructor
@Log4j2
public class BookRestController {


	private final LibraryService libraryService;
	private final BookRepository bookRepository;

    @GetMapping(value = "/api/books")
    public Mono<Page<Book>> findAll(@RequestParam("page") int page, @RequestParam("size") int size) {
    	var request = PageRequest.of(page, size);
    	
        return this.bookRepository.findAllBy(request)
				.collectList().zipWith(this.bookRepository.count())
				.map(t -> new PageImpl<>(t.getT1(), request, t.getT2()));
    }
    
    @GetMapping("/api/book")
    public Flux<Book> fildAll(){
		return this.bookRepository.findAll();
    }

    @GetMapping(value = "/api/book/{id}")
    public Mono<Book> findById(@PathVariable("id") String id) {
        return this.libraryService.getBookById(id);
    }
    
    @PostMapping("/api/book/{bookId}")
    public Mono<Comment> saveComment(@PathVariable String bookId, @RequestBody String title) {
    	log.info("getting  bookId " + bookId + " title comment " + title);
    	return this.libraryService.addComment(bookId, title);
    }

    @PostMapping(value = "/api/book", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Book> saveAndUpdate(@RequestBody Book book) {
        return this.libraryService.addBook(book);
    }

    @DeleteMapping(value = "/api/book")
    public Mono<Void> deleteById(@RequestParam("id") String id) {
        return this.libraryService.deleteBookById(id);
        
    }
}
