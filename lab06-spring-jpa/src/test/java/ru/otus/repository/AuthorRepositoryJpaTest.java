package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import ru.otus.domain.Author;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class AuthorRepositoryJpaTest {

    private static final String AUTHOR_NAME = "Тестовый Автор";

    @Autowired
    AuthorRepository authorRepository;

    @Test
    @DisplayName("Тестирование записи Автора в репозиторий")
    @Disabled
    void testAuthorSave(){
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Author authorFromRepo =  authorRepository.save(author);
        Assertions.assertEquals(author,authorFromRepo);
    }
}