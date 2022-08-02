package ru.otus.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import ru.otus.model.Comment;

@RequiredArgsConstructor
@Component
public class CustomCommentRepositoryImpl implements CustomCommentRepository{

    private final MongoTemplate mongoTemplate;

    @Override
    public void updateCommentById(String id, String title) {
        val query = new Query().addCriteria(Criteria.where("_id").is(id));
        var update = new Update();
        update.set("title", title);
        mongoTemplate.update(Comment.class).matching(query).apply(update).first();
    }

    @Override
    public void deleteCommentsByBookId(String bookId) {
        val query = new Query().addCriteria(Criteria.where("book.id").is(bookId));
        mongoTemplate.findAllAndRemove(query,Comment.class);
    }
}
