package ru.otus.repository;

import ru.otus.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    void delete(long commentId);
    Comment save(Comment domain);
    List<Comment> findAll();
    Optional<Comment> findById(long id);
    List<Comment> findByBookId(long bookId);
    Comment addCommentBookById(long bookId, String title);
    void updateCommentById(long id, String title);
}
