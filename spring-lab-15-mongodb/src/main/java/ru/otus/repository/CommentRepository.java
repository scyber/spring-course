package ru.otus.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import java.util.List;
import java.util.Optional;

public interface CommentRepository extends MongoRepository<Comment,String> {

    void deleteById(@Param("id") String id);

    Comment save(Comment domain);

    Optional<Comment> findById(@Param("id") String id);

    @Query("select c from Comment c where c.book = :book ")
    List<Comment> findByBook(@Param("book") Book book);

    @Query("update Comment c set c.title = :title where c.id = :id")
    void updateCommentById(@Param("id") String id, @Param("title") String title);

}
