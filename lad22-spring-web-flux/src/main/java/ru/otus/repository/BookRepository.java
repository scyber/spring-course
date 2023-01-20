package ru.otus.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;

public interface BookRepository extends ReactiveMongoRepository<Book,String> {


    Mono<Book> findById(String id);
    Mono<Void> deleteById(String id);
    Mono<Void> delete(Book book);
    Flux<Book> findByTitle(String title);
    Flux<Book> findAll();
    Flux<Book> findAllBy(Pageable pageable);

}
