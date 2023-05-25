package ru.otus.repository;


import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.domain.Book;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class BookRepositoryTest {

    private static final String BOOK_TITLE = "Test Title";

    @Autowired
    private BookRepository bookRepository;

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
    @DisplayName("Test BookRepositoryCustom find Book by Title")
    public void testFindBookByTitle(){
        var book = new Book();
        book.setTitle(BOOK_TITLE);
        var savedBook = bookRepository.save(book).block();
        var foundBooks = bookRepository.findByTitle(BOOK_TITLE).collectList().block();
        Assertions.assertTrue(foundBooks.contains(savedBook));
    }
}
