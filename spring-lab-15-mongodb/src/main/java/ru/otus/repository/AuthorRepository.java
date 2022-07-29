package ru.otus.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Author;
import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends MongoRepository<Author,String> {

    Author save(Author domain);

    List<Author> findAll();

    Optional<Author> findById(String id);

    @Query(value = "{name : :#{#name} }")
    List<Author> findByNameLike(String name);

    void deleteById(String id);

    @Query("{_id: :#{#id}, 'name': :#{#name}}")
    void updateNameById(@Param("id") String id, @Param("name")  String name);
}
