package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.AuthorDao;
import ru.otus.domain.Author;


import java.util.stream.Collectors;

@JdbcTest
@Import(AuthorDao.class)
public class AuthorDaoTest {

    private static final String TEST_AUTHOR_NAME = "TEST_AUTHOR";
    private static final String AUTHOR_TO_DEL = "Автора на удаление";

    @Autowired
    private AuthorDao authorDao;


    @Test
    @DisplayName("Тест создания и сохранения нового автора")
    void createGenre(){
        Author author = new Author();
        author.setName(TEST_AUTHOR_NAME);
        long genreId = authorDao.save(author);
        Author authorFromDao = authorDao.findById(genreId).get();
        Assertions.assertEquals(TEST_AUTHOR_NAME, authorFromDao.getName());
    }

    @Test
    @DisplayName("Тест удаление автора")
    void deleteGenre(){
        Author author = new Author();
        author.setName(AUTHOR_TO_DEL);
        long authorId = authorDao.save(author);
        authorDao.delete(authorId);
        Assertions.assertTrue(!authorDao.findAll().stream().map(a -> a.getId()).collect(Collectors.toList()).contains(authorId));
    }

}
