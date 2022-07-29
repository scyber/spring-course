package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre,String> {

    Genre save(Genre domain);

    List<Genre> findAll();

    @Query("{_id : :#{#id} }")
    Optional<Genre> findById(@Param("id") String id);

    @Query("{'name' : :#{#name} }")
    List<Genre> findByName(@Param("name") String name);

    @Query("{_id : :#{#id} }")
    void deleteById(@Param("id") Long id);
}
