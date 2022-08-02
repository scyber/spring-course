package ru.otus.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.model.Genre;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class GenreRepositoryTest {

    private static final String GENRE_NAME = "Тестовый Автор";
    private static final String GENRE_FOR_DEL = "Жанр на удаление";

    @Autowired
    GenreRepository genreRepository;

    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();
    }

    @AfterAll
    static void clean() {
        mongoDBContainer.stop();
    }

    @Test
    @DisplayName("Тестирование добавления жанра")
    void testAddGenre() {
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var genreFromSave = genreRepository.save(genre);
        assertEquals(GENRE_NAME, genreFromSave.getName());
    }

    @Test
    @DisplayName("Тест удаления жанра")
    void testDeleteGenre() {
        Genre genre = new Genre();
        genre.setName(GENRE_FOR_DEL);
        var genreFromSave = genreRepository.save(genre);
        genreRepository.deleteById(genreFromSave.getId());
        var genres = genreRepository.findByName(GENRE_FOR_DEL);
        assertTrue(!genres.contains(genreFromSave));
    }

    @Test
    @DisplayName("Тестирование получения всех жанров")
    void testGetAllGenres() {
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        genreRepository.save(genre);
        var genres = genreRepository.findAll().stream().map(g -> g.getName()).collect(Collectors.toList());
        Assertions.assertTrue(genres.contains(GENRE_NAME));
    }

    @Test
    @DisplayName("Тестирование поиска по имени жанра")
    void testFindByNameGenres() {
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        genreRepository.save(genre);
        var genres = genreRepository.findByName(GENRE_NAME).stream().map(g -> g.getName()).collect(Collectors.toList());
        assertTrue(genres.contains(GENRE_NAME));
    }

    @Test
    @DisplayName("Тест поиска по id жанра")
    void testFindByIdGenre() {
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var genreFromSave = genreRepository.save(genre);
        var id = genreFromSave.getId();
        assertEquals(genreFromSave.getName(), genreRepository.findById(id).get().getName());
    }
}