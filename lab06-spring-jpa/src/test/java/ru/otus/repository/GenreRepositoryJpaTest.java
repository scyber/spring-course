package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Genre;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
class GenreRepositoryJpaTest {

    private static final String GENRE_NAME = "Тестовый Автор";
    private static final String GENRE_FOR_DEL = "Автор на удаление";

    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Тестирование добавления жанра")
    void testAddGenre(){
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var genreFromSave = genreRepositoryJpa.save(genre);
        assertNotNull(genreFromSave);
    }
    @Test
    @DisplayName("Тест удаления жанра")
    void testDeleteGenre(){
        Genre genre = new Genre();
        genre.setName(GENRE_FOR_DEL);
        var genreFromSave = genreRepositoryJpa.save(genre);
        genreRepositoryJpa.delete(genreFromSave.getId());
        var genres = genreRepositoryJpa.findByName(GENRE_FOR_DEL);
        assertTrue(!genres.contains(genreFromSave));
    }
    @Test
    @DisplayName("Тестирование получения всех жанров")
    void testGetAllGenres(){
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        genreRepositoryJpa.save(genre);
        var genres = genreRepositoryJpa.findAll();
        Assertions.assertTrue(genres.contains(genre));
    }
    @Test
    @DisplayName("Тестирование поиска по имени жанра")
    void testFindByNameGenres(){
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        genreRepositoryJpa.save(genre);
        var genres = genreRepositoryJpa.findByName(GENRE_NAME);
        assertTrue(genres.contains(genre));
    }
    @Test
    @DisplayName("Тест поиска по id жанра")
    void testFindByIdGenre(){
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var genreFromSave =  genreRepositoryJpa.save(genre);
        var id = genreFromSave.getId();
        assertEquals(genreFromSave,genreRepositoryJpa.findById(id).get());
    }
}