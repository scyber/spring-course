package ru.otus.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment,String>,CommentRepositoryCustom {
	

    Mono<Void> deleteById(String id);

    Mono<Comment> save(Comment comment);

    Mono<Comment> findById(String id);

}
