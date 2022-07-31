package ru.otus.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.otus.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book,String> {

    Book save(Book domain);
    Optional<Book> findById(String id);

    List<Book> findAll();
    void deleteById(String id);
    void delete(Book book);
    List<Book> findByTitle(String title);

    @Query("{_id: :#{#id}, 'title': :#{#title}}")
    void updateBookTitleById(@Param("id") String id, @Param("title") String title);
}
