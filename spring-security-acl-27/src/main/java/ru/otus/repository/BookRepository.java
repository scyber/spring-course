package ru.otus.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookRepository extends PagingAndSortingRepository<Book,Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    Optional<Book> findById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Book book);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    List<Book> findByTitle(String title);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    List<Book> findAll();

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    Page<Book> findAll(Pageable pageable);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Book save(Book book);

    @Modifying
    @Query("update Book b set b.title = :title where b.id = :id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateBookTitleById(@Param("id") Long id,@Param("title") String title);

}
