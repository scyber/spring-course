package ru.otus.repositories;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Comment;

public interface CommentRepositoryCustom {

	Mono<Comment> updateCommentById(String id, String title);
	Flux<Comment> deleteCommentsByBookId(String bookId);
	Flux<Comment> findCommentsByBookId(String bookId);
	Mono<Comment> addCommentByBookId(String bookId, String text);
}
