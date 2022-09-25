package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import ru.otus.domain.Genre;
import java.util.List;
import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre,Long> {

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Genre save(Genre domain);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    List<Genre> findAll();

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    Optional<Genre> findById(@Param("id") Long id);

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    List<Genre> findByName(@Param("name") String name);

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void deleteById(@Param("id") Long id);

}
