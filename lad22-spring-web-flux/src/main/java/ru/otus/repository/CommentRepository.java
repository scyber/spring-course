package ru.otus.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;

public interface CommentRepository extends ReactiveMongoRepository<Comment,String> {

    Mono<Void> deleteById(@Param("id") String id);

    Mono<Comment> save(Comment comment);

    Mono<Comment> findById(@Param("id") String id);

    @Query("select c from Comment c where c.book = :book ")
    Flux<Comment> findByBook(@Param("book") Book book);

    @Query("update Comment c set c.title = :title where c.id = :id")
    @Modifying
    Mono<Void> updateCommentById(@Param("id")String id, @Param("title") String title);

}
