package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.domain.Genre;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class GenreRepositoryTest {

    private static final String GENRE_NAME = "Тестовый Автор";
    private static final String GENRE_FOR_DEL = "Автор на удаление";

    @Autowired
    GenreRepository genreRepository;

    @Autowired
    TestEntityManager em;


    @Test
    @DisplayName("Тестирование добавления жанра")
    void testAddGenre(){
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var genreFromSave = genreRepository.save(genre);
        assertNotNull(genreFromSave);
    }
    @Test
    @DisplayName("Тест удаления жанра")
    void testDeleteGenre(){
        Genre genre = new Genre();
        genre.setName(GENRE_FOR_DEL);
        var genreFromSave = genreRepository.save(genre);
        genreRepository.deleteById(genreFromSave.getId());
        var genres = genreRepository.findByName(GENRE_FOR_DEL);
        assertTrue(!genres.contains(genreFromSave));
    }
    @Test
    @DisplayName("Тестирование получения всех жанров")
    void testGetAllGenres(){
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        genreRepository.save(genre);
        var genres = genreRepository.findAll();
        Assertions.assertTrue(genres.contains(genre));
    }
    @Test
    @DisplayName("Тестирование поиска по имени жанра")
    void testFindByNameGenres(){
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        genreRepository.save(genre);
        var genres = genreRepository.findByName(GENRE_NAME);
        assertTrue(genres.contains(genre));
    }
    @Test
    @DisplayName("Тест поиска по id жанра")
    void testFindByIdGenre(){
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var genreFromSave =  genreRepository.save(genre);
        var id = genreFromSave.getId();
        assertEquals(genreFromSave, genreRepository.findById(id).get());
    }
}