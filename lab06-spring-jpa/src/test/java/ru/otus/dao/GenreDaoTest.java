package ru.otus.dao;


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
public class GenreDaoTest {
    private static final String TEST_GENRE = "Тестовый жанр";
    private static final String TEST_GENRE_DEL = "Тестовый жанр на создание и удаление";

    @Autowired
    private GenreDao genreDao;


    @Test
    @DisplayName("Тест создания и поиска жанра")
    void testAddGenre(){
        Genre genre = new Genre();
        genre.setGenreName(TEST_GENRE);
        long genreId = genreDao.save(genre);
        Genre genreFromDao = genreDao.findById(genreId).get();
        Assertions.assertEquals(genre.getGenreName(),genreFromDao.getGenreName());
    }
    @Test
    @DisplayName("Тест удаление жанра")
    @Order(2)
    void deleteGenre(){
        Genre genre = new Genre();
        genre.setGenreName(TEST_GENRE_DEL);
        long genreId = genreDao.save(genre);
        genreDao.delete(genreId);
        Assertions.assertTrue(!genreDao.findAll().stream().map(g -> g.getId()).collect(Collectors.toList()).contains(genreId));
    }
}
