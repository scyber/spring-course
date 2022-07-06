package ru.otus;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Genre;

import java.util.stream.Collectors;

@JdbcTest
@Import(GenreDao.class)
public class GenreTest {
    private static final String TEST_GENRE = "Тестовый жанр";
    @Autowired
    private GenreDao genreDao;
    private long genreId;

    @Test
    @DisplayName("Тест создания и поиска жанра")
    @Order(1)
    void testAddGenre(){
        Genre genre = new Genre();
        genre.setGenreName(TEST_GENRE);
        genreId = genreDao.save(genre);
        Genre genreFromDao = genreDao.findById(genreId);
        Assertions.assertEquals(genre.getGenreName(),genreFromDao.getGenreName());
    }
    @Test
    @DisplayName("Тест удаление жанра")
    @Order(2)
    void deleteGenre(){
        genreDao.delete(genreId);
        Assertions.assertTrue(!genreDao.findAll().stream().map(genre -> genre.getId()).collect(Collectors.toList()).contains(genreId));
    }
}
