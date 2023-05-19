package ru.otus.repository;

import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import ru.otus.domain.Book;

@Component
@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom{
	
	private final ReactiveMongoTemplate mongoTemplate;

	@Override
	public Flux<Book> findByTitle(String title) {
		var query = new Query().addCriteria(Criteria.where("title").is(title));
		return this.mongoTemplate.find(query, Book.class);
	}

}
