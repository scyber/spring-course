package ru.otus.repository;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;

import ru.otus.domain.Book;
import ru.otus.domain.Comment;


@Disabled
@DataMongoTest
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class CommentRepositoryTest {

   

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;
    
    private static final String TITLE_COMMENT = "Комментарий тестовый";


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
    void testAddComment(){
//       var book = new Book();
//       var savedBook = bookRepository.save(book).block();
//       var bookId = savedBook.getId();
//       var comment = new Comment();
//       comment.setBook(savedBook);
//       comment.setTitle(TITLE_COMMENT);
//       var savedComment = commentRepository.save(comment);
//       System.out.println(comment.getTitle());
       //Assertions.assertEquals(, savedComment.block().getTitle());

    }
//    @Test
//    @DisplayName("Тест сохранения и поиска комментария")
//    void testFindComment(){
//        var book = bookRepository.findById(1L).get();
//        var comment = new Comment();
//        comment.setBook(book);
//        comment.setTitle(TITLE_COMMENT);
//        commentRepository.save(comment);
//        var foundComments = commentRepository.findByBook(book);
//        Assertions.assertTrue(foundComments.contains(comment));
//    }
//    @Test
//    @DisplayName("Тестирование удаления комментария")
//    void deleteComment(){
//        var book = bookRepository.findById(1L).get();
//        var comment = new Comment();
//        comment.setTitle(TITLE_COMMENT);
//        comment.setBook(book);
//        var savedComment = commentRepository.save(comment);
//        commentRepository.delete(comment);
//        var comments = commentRepository.findAll();
//        Assertions.assertFalse(comments.contains(savedComment));
//    }
}