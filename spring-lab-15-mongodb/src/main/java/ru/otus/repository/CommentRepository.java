package ru.otus.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ru.otus.model.Comment;
import java.util.List;


public interface CommentRepository extends MongoRepository<Comment,String>, CustomCommentRepository {


    @Query(value = "{'book.id' : ?0 }")
    List<Comment> findByBookId(String bookId);


}
