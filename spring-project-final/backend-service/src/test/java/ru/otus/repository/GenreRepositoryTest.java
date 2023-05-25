package ru.otus.repository;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.domain.Genre;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class GenreRepositoryTest {

    private static final String GENRE_NAME = "Test Genre";

    @Autowired
    private GenreRepository genreRepository;

    private static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.9");

    @DynamicPropertySource
    private static void setProperties(DynamicPropertyRegistry registry) {
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
    @DisplayName("Testing Custom Genre repository find Genre by Name")
    public void testCustomGenreRepository(){
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        var savedGenre = genreRepository.save(genre).block();
        var foundGenre = genreRepository.findByName(GENRE_NAME).collectList().block();
        Assertions.assertTrue(foundGenre.contains(savedGenre));
    }
}
