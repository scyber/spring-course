package ru.otus.repository;

import java.util.Optional;

public interface ItemRepository<T> {
    Optional<T> findById(long id);
    Iterable<T> findByName(String name);
    Iterable<T> findAll();
    long updateNameById(long id, String name);
    void delete(T domain);
    T save(T domain);
}
