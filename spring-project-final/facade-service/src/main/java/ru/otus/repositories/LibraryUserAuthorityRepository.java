package ru.otus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.LibraryUserAuthority;

import java.util.List;

public interface LibraryUserAuthorityRepository extends JpaRepository<LibraryUserAuthority, Long> {
    List<LibraryUserAuthority> findAll();
}