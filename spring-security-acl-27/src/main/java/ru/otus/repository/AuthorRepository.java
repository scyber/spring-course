package ru.otus.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.domain.Author;
import java.util.List;
import java.util.Optional;


public interface AuthorRepository extends PagingAndSortingRepository<Author,Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Author save(Author domain);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    List<Author> findAll();

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    Optional<Author> findById(Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    @Query("select a from Author a where a.name = :name")
    List<Author> findByName(@Param("name") String name);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(@Param("id") Long id);

    @Modifying
    @Query("update Author a set a.name = :name where a.id = :id")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void updateNameById(@Param("id") long id, @Param("name") String name);
}
