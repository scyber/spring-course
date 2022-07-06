package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;


import java.util.stream.Collectors;

@SpringBootTest
public class AuthorTest {

    private static final String TEST_AUTHOR_NAME = "TEST_AUTHOR";

    @Autowired
    private AuthorDao authorDao;
    private long genreId;

    @Test
    @DisplayName("Тест создания и сохранения нового автора")
    @Order(1)
    void createGenre(){
        Author author = new Author();
        author.setName(TEST_AUTHOR_NAME);
        genreId = authorDao.save(author);
        Author authorFromDao = authorDao.findById(genreId).get();
        Assertions.assertEquals(TEST_AUTHOR_NAME, authorFromDao.getName());
    }

    @Test
    @DisplayName("Тест удаление автора")
    @Order(2)
    void deleteGenre(){
        authorDao.delete(genreId);
        Assertions.assertTrue(!authorDao.findAll().stream().map(a -> a.getId()).collect(Collectors.toList()).contains(genreId));
    }

}
