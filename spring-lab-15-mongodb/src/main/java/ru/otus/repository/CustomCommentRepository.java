package ru.otus.repository;

import org.springframework.data.repository.query.Param;

public interface CustomCommentRepository {

    void updateCommentById(String id, String title);
    void deleteCommentsByBookId(String bookId);
}
