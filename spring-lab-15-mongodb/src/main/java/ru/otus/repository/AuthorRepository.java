package ru.otus.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.model.Author;
import java.util.List;



public interface AuthorRepository extends MongoRepository<Author,String>,CustomAuthorRepository {


    @Query(value = "{'name' : :#{#name} }")
    List<Author> findByNameLike(@Param("name") String name);

}
