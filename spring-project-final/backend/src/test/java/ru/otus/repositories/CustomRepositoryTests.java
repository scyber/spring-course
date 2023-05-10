package ru.otus.repositories;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;


@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class CustomRepositoryTests {

    private static final String AUTHOR_NAME = "Test Author";

    private static final String GENRE_NAME = "Test Genre";

    private static final String BOOK_TITLE = "Test Title";

    private static final String COMMENT_TITLE = "Test Comment";

    private static final String COMMENT_FOR_UPDATE = "Updated Comment";

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;

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
    @DisplayName("Testing Custom Author Repository find Author by Name")
    public void testCustomAuthorRepository(){
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var savedAuthor = authorRepository.save(author).block();
        var foundAuthors = authorRepository.findByName(AUTHOR_NAME).collectList().block();
        Assertions.assertTrue(foundAuthors.contains(savedAuthor));
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

    @Test
    @DisplayName("Test BookRepositoryCustom find Book by Title")
    public void testFindBookByTitle(){
        var book = new Book();
        book.setTitle(BOOK_TITLE);
        var savedBook = bookRepository.save(book).block();
        var foundBooks = bookRepository.findByTitle(BOOK_TITLE).collectList().block();
        Assertions.assertTrue(foundBooks.contains(savedBook));
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