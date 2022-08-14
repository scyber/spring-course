package org.example.repository;


import java.util.Map;
import java.util.Optional;

public interface QuizRepository<T> {
    Optional<T> findById(Long id);
    Map<Long,T> getItems();
}
