package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.model.Genre;
import java.util.List;

public interface GenreRepository extends MongoRepository<Genre,String> {


    @Query("{'name' : :#{#name} }")
    List<Genre> findByName(@Param("name") String name);

    @Query("{_id : :#{#id} }")
    void deleteById(@Param("id") Long id);
}
