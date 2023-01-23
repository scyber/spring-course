package ru.otus.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment,String>,CommentRepositoryCustom {
	

    Mono<Void> deleteById(@Param("id") String id);

    Mono<Comment> save(Comment comment);

    Mono<Comment> findById(@Param("id") String id);

    Flux<Comment> findAllBy(@Param("book") Book book);

    @Query(value = "{'book.id' : ?0 }")
    Flux<Comment> findAllByBookId(String bookId);
}
