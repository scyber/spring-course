package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Comment;

import java.util.Optional;


@DataJpaTest
@Import({CommentRepositoryJpa.class, BookRepositoryJpa.class})
class CommentRepositoryJpaTest {

    private static final long BOOK_ID = 1L;

    @Autowired
    private TestEntityManager em;
    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;
    @Autowired
    private CommentRepositoryJpa commentRepository;

    private static final String TITLE_COMMENT = "Комментарий тестовый";
    private static final String TITLE_COMMENT_UPDATE = "Обнолвленный тестовый комментарий";




    @Test
    @DisplayName("Тест добавления комментария к книге")
    void testAddComment(){
       var comment = new Comment();
       var book = bookRepositoryJpa.findById(BOOK_ID).get();
       comment.setBook(book);
       comment.setTitle(TITLE_COMMENT);
       var savedComment = commentRepository.save(comment);
       var commentId = savedComment.getId();
       var foundComment = commentRepository.findById(commentId);
       Assertions.assertEquals(TITLE_COMMENT, foundComment.get().getTitle());
       var listOfComments = commentRepository.findByBookId(1L);
       Assertions.assertTrue(listOfComments.contains(savedComment));
    }
    @Test
    @DisplayName("Тест сохранения и удаления комментария к книге")
    void deleteComment(){
        var comment = new Comment();
        var book = bookRepositoryJpa.findById(BOOK_ID).get();
        comment.setBook(book);
        comment.setTitle(TITLE_COMMENT);
        var savedComment = commentRepository.save(comment);
        var commentId = savedComment.getId();
        var foundComment = commentRepository.findById(commentId);
        commentRepository.delete(commentId);
        em.detach(savedComment);
        var emptyComment = commentRepository.findById(commentId);
        Assertions.assertEquals(Optional.empty(),emptyComment);
    }
    @Test
    @DisplayName("Тест обновления комментария к книге")
    void testUpdateComment(){
        var comment = new Comment();
        var book = bookRepositoryJpa.findById(BOOK_ID).get();
        comment.setBook(book);
        comment.setTitle(TITLE_COMMENT);
        var savedComment = commentRepository.save(comment);
        var commentId = savedComment.getId();
        commentRepository.updateCommentById(commentId,TITLE_COMMENT_UPDATE);
        em.detach(savedComment);
        var updatedComment = commentRepository.findById(commentId).get();
        Assertions.assertEquals(TITLE_COMMENT_UPDATE,updatedComment.getTitle());
    }
}