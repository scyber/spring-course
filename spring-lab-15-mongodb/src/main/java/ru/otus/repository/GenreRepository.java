package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre,String> {

    Genre save(Genre domain);

    List<Genre> findAll();

    Optional<Genre> findById(@Param("id") String id);

    List<Genre> findByName(@Param("name") String name);

    void deleteById(@Param("id") Long id);
}
