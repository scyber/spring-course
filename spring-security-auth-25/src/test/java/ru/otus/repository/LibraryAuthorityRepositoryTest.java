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
class LibraryAuthorityRepositoryTest {

    @Autowired
    private LibraryAuthorityRepository libraryAuthorityRepository;

    @Test
    @DisplayName("Тестирование authority записей в базе ")
    void testAuthority() {
        var authority = libraryAuthorityRepository.findByUserId(3L);
        var libraryAuthority = authority.get(0);
        Assertions.assertEquals("ROLE_TEST",libraryAuthority.getAuthority());
    }
}