package ru.otus.repository;

import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@RequiredArgsConstructor
public class BookRepositoryCustomImpl implements BookRepositoryCustom{

    private final MongoTemplate mongoTemplate;

    @Override
    public List<Author> getAuthorsByBookId(String bookId) {
        val aggregation = newAggregation(
                match(Criteria.where("id").is(bookId)),
                unwind("authors"),
                project().andExclude("_id").and("author.id").as("_id").and("author.name").as("name")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Author.class).getMappedResults();
    }

    @Override
    public List<Genre> getGenresByBookId(String bookId) {
        val aggregation = newAggregation(
                match(Criteria.where("id").is(bookId)),
                unwind("genres"),
                project().andExclude("_id").and("genre.id").as("id").and("genre.name").as("name")
        );
        return mongoTemplate.aggregate(aggregation, Book.class, Genre.class).getMappedResults();
    }

    @Override
    public void updateBookTitleById(String id, String title) {
        val query = new Query().addCriteria(Criteria.where("_id").is(id));
        var update = new Update();
        update.set("title",title);
        mongoTemplate.update(Book.class).matching(query).apply(update).first();
    }
}
