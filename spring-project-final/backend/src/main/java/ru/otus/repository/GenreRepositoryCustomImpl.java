package ru.otus.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import ru.otus.domain.Genre;

@Component
@RequiredArgsConstructor
public class GenreRepositoryCustomImpl implements GenreRepositoryCustom{

    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Flux<Genre> findByName(String name) {
        var query = new Query().addCriteria(Criteria.where("name").is(name));
        return mongoTemplate.find(query,Genre.class);
    }
}
