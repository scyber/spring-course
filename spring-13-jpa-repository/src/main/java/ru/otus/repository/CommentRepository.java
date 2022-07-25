package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    void deleteById(@Param("id") Long id);
    Comment save(Comment domain);

    List<Comment> findAll();

    Optional<Comment> findById(@Param("id") Long id);

    @Query("select c from Comment c where c.book_id =:book_id ")
    @Transactional
    List<Comment> findByBookId(@Param("book_id") Long bookId);

    void addBookComment(Long bookId, String text);
}
