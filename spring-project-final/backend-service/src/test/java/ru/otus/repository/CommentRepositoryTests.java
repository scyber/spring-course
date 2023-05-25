package ru.otus.repository;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.domain.Book;


@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class CommentRepositoryTests {

    private static final String BOOK_TITLE = "Test Title";

    private static final String COMMENT_TITLE = "Test Comment";

    private static final String COMMENT_FOR_UPDATE = "Updated Comment";

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

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
    @DisplayName("Test CommentsCustom methods")
    public void testCustomCommentsRepository(){
        var book = new Book();
        book.setTitle(BOOK_TITLE);
        var savedBookId = bookRepository.save(book).block().getId();
        var comment = commentRepository.addCommentByBookId(savedBookId,COMMENT_TITLE).block();
        var foundComments = commentRepository.findCommentsByBookId(savedBookId).collectList().block();
        Assertions.assertTrue(foundComments.contains(comment));
        commentRepository.updateCommentById(comment.getId(), COMMENT_FOR_UPDATE).block();
        var listTitles = commentRepository.findCommentsByBookId(savedBookId).map(c -> c.getTitle()).collectList().block();
        Assertions.assertTrue(listTitles.contains(COMMENT_FOR_UPDATE));
        commentRepository.deleteCommentsByBookId(savedBookId).collectList().block();
        var emptyComments = commentRepository.findCommentsByBookId(savedBookId).collectList().block();
        Assertions.assertTrue(emptyComments.isEmpty());

    }

}