package ru.otus.repository;

public interface CommonRepository<T>{
    public Long save(T domain);
    public void delete(Long id);
    public T findById(Long id);
    public Iterable<T> findAll();
}
