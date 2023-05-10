package ru.otus.repositories;


import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.otus.domain.Author;

@RequiredArgsConstructor
@Component
public class AuthorRepositoryCustomImpl implements AuthorRepositoryCustom{

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Author> findByName(String name) {
        var query = new Query().addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query, Author.class);
    }
}
