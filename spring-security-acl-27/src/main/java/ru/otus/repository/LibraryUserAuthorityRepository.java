package ru.otus.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.LibraryUserAuthority;

public interface LibraryUserAuthorityRepository extends JpaRepository<LibraryUserAuthority, Long> {
    List<LibraryUserAuthority> findAll();
}