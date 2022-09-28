package ru.otus.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.model.Author;

public class CustomAuthorRepositoryImpl implements CustomAuthorRepository{

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void updateAuthorName(String id, String name) {
        var query = new Query().addCriteria(Criteria.where("_id").is(id));
        var update = new Update();
        update.set("name", name);
        this.mongoTemplate.update(Author.class).matching(query).apply(update).first();
    }
}
