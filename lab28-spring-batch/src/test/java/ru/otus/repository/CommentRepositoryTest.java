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
import ru.otus.domain.Comment;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@Disabled
class CommentRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository bookRepository;


    private static final String TITLE_COMMENT = "Комментарий тестовый";


    @Autowired
    private CommentRepository commentRepository;


    @Test
    @DisplayName("Тест добавления комментария")
    void testAddComment(){
       var book = bookRepository.findById(1L).get();
       var comment = new Comment();
       comment.setBook(book);
       comment.setTitle(TITLE_COMMENT);
       var savedComment = commentRepository.save(comment);

    }
    @Test
    @DisplayName("Тест сохранения и поиска комментария")
    void testFindComment(){
        var book = bookRepository.findById(1L).get();
        var comment = new Comment();
        comment.setBook(book);
        comment.setTitle(TITLE_COMMENT);
        commentRepository.save(comment);
        var foundComments = commentRepository.findByBook(book);
        Assertions.assertTrue(foundComments.contains(comment));
    }
    @Test
    @DisplayName("Тестирование удаления комментария")
    void deleteComment(){
        var book = bookRepository.findById(1L).get();
        var comment = new Comment();
        comment.setTitle(TITLE_COMMENT);
        comment.setBook(book);
        var savedComment = commentRepository.save(comment);
        commentRepository.delete(comment);
        var comments = commentRepository.findAll();
        Assertions.assertFalse(comments.contains(savedComment));
    }
}