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
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.model.Genre;

import java.util.List;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class CommentRepositoryTest {

    private static final String TITLE_COMMENT = "Комментарий тестовый";
    private static final String TITLE_COMMENT_FOR_DEL = "Комментарий на удаление";
    private static final String TITLE_COMMENT_FOR_ADD = "Комментарий на добавление";
    private static final String BOOK_TITLE = "TEST BOOK FOR COMMENTS";
    private static final String BOOK_CASCADE_COMMENTS = "CASCADE REMOVE COMMENTS";
    private static final String AUTHOR_NAME = "AUTHOR FOR COMMENTS ";
    private static final String GENRE_NAME = "GENRE FOR COMMENT";

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
    @DisplayName("Тест добавления комментария")
    void testAddComment() {
        var book = new Book();
        book.setTitle(BOOK_TITLE);
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        var savedBook = bookRepository.save(book);
        var comment = new Comment();
        comment.setBook(savedBook);
        comment.setTitle(TITLE_COMMENT_FOR_ADD);
        var savedComment = commentRepository.save(comment);
        var secondComment = new Comment();
        secondComment.setTitle(TITLE_COMMENT);
        secondComment.setBook(savedBook);
        commentRepository.save(secondComment);
        Assertions.assertEquals(TITLE_COMMENT_FOR_ADD, savedComment.getTitle());
    }

    @Test
    @DisplayName("Тест сохранения и поиска комментария")
    void testFindComment() {
        var book = new Book();
        book.setTitle(BOOK_TITLE);
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        var savedBook = bookRepository.save(book);
        var comment = new Comment();
        comment.setBook(savedBook);
        comment.setTitle(TITLE_COMMENT_FOR_DEL);
        var savedComment = commentRepository.save(comment);
        var foundComments = commentRepository.findByBookId(savedBook.getId());
        Assertions.assertTrue(foundComments.contains(savedComment));
        commentRepository.deleteCommentsByBookId(savedBook.getId());
        var emptyListComments = commentRepository.findByBookId(savedBook.getId());
        Assertions.assertTrue(emptyListComments.isEmpty());
    }

    @Test
    @DisplayName("Тестирование удаления комментария")
    void deleteComment() {
        var book = new Book();
        book.setTitle(BOOK_TITLE);
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        var savedBook = bookRepository.save(book);
        var comment = new Comment();
        comment.setTitle(TITLE_COMMENT);
        comment.setBook(savedBook);
        var savedComment = commentRepository.save(comment);
        commentRepository.delete(savedComment);
        var comments = commentRepository.findAll();
        Assertions.assertFalse(comments.contains(savedComment));
    }

    @Test
    void testRemoveAllBookComments() {
        var book = new Book();
        book.setTitle(BOOK_CASCADE_COMMENTS);
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        var savedBook = bookRepository.save(book);
        var id = savedBook.getId();
        var comment = new Comment();
        comment.setTitle(TITLE_COMMENT);
        comment.setBook(savedBook);
        commentRepository.save(comment);
        var secondComment = new Comment();
        secondComment.setTitle(TITLE_COMMENT_FOR_ADD);
        secondComment.setBook(savedBook);
        commentRepository.save(secondComment);
        commentRepository.deleteCommentsByBookId(id);
        var emptyComments = commentRepository.findByBookId(id);
        Assertions.assertTrue(emptyComments.isEmpty());
    }
}