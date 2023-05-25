package ru.otus.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.domain.Author;


@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class AuthorRepositoryTest {

    private static final String AUTHOR_NAME = "Test Author";

    @Autowired
    private AuthorRepository authorRepository;

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
    @DisplayName("Testing Custom Author Repository find Author by Name")
    public void testCustomAuthorRepository(){
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var savedAuthor = authorRepository.save(author).block();
        var foundAuthors = authorRepository.findByName(AUTHOR_NAME).collectList().block();
        Assertions.assertTrue(foundAuthors.contains(savedAuthor));
    }
}