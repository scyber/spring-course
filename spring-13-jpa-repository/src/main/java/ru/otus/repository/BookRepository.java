package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book,Long> {

    Book save(Book domain);
    Optional<Book> findById(Long id);
    List<Book> findAll();
    void deleteById(Long id);
    void delete(Book book);
    List<Book> findByTitle(String title);

    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    void updateBookTitleById(@Param("id") Long id,@Param("title") String title);
}
