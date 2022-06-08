package org.example.quiz.repository;

import java.io.IOException;
import java.util.List;

public interface QuizRepository<T> {
    T findById(Long id) throws IOException;
    List<T> findByQuestionId(Long id);
}
