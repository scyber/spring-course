package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;
import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends JpaRepository<Author,Long> {

    Author save(Author domain);

    List<Author> findAll();

    Optional<Author> findById(Long id);

    @Query("select a from Author a where a.name = :name")
    @Transactional
    List<Author> findByName(@Param("name") String name);


    void deleteById(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Author a set a.name = :name where a.id = :id")
    void updateNameById(@Param("id") long id, @Param("name") String name);
}
