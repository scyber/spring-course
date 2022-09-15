package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class LibraryUserRepositoryTest {

    @Autowired
    LibraryUserRepository libraryUserRepository;

    @Test
    @DisplayName("Тестирование users записей в базе ")
    void testAuthorSave() {
        var libraryUser = libraryUserRepository.findByUserName("testuser");
        Assertions.assertEquals("testuser", libraryUser.getUsername());
    }
}