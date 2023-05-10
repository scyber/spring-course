package ru.otus.repositories;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;


@Component
@RequiredArgsConstructor
public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

	private final ReactiveMongoTemplate reactiveMongoTemplate;

	@Override
	public Flux<Comment> deleteCommentsByBookId(String bookId) {
		var query = new Query().addCriteria(Criteria.where("book.id").is(bookId));
		return this.reactiveMongoTemplate.findAllAndRemove(query, Comment.class);
	}

	@Override
	public Mono<Comment> updateCommentById(String id, String title) {
		var query = new Query().addCriteria(Criteria.where("_id").is(id));
		var update = new Update();
		update.set("title", title);
		return this.reactiveMongoTemplate.findAndModify(query, update, Comment.class);
	}

	@Override
	public Flux<Comment> findCommentsByBookId(String bookId) {
		var query = new Query().addCriteria(Criteria.where("book._id").is(bookId));
		return this.reactiveMongoTemplate.find(query, Comment.class, "comments");
	}

	@Override
	public Mono<Comment> addCommentByBookId(String bookId, String text) {
		var comment = new Comment();
		comment.setTitle(text);
		return this.reactiveMongoTemplate.findById(bookId, Book.class).flatMap(book -> {
			comment.setBook(book);
			return this.reactiveMongoTemplate.save(comment);
		});

	}

}
