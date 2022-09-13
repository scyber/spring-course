package ru.otus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.domain.LibraryUser;

public interface LibraryUserRepository extends JpaRepository<LibraryUser, Long> {

    LibraryUser findByUserName(String userName);

}
