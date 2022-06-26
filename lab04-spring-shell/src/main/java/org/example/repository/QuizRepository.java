package org.example.repository;

import java.io.IOException;
import java.util.Optional;

public interface QuizRepository<T> {
    Optional<T> findById(Long id) throws IOException;

}
