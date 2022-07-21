package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Comment;


@DataJpaTest
@Import({CommentRepositoryJpa.class})
class CommentRepositoryJpaTest {

    private final static long BOOK_ID = 1L;

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CommentRepositoryJpa commentRepositoryJpa;

    private static final String TITLE_COMMENT = "Комментарий тестовый";
    private static final String TITLE_COMMENT_UPDATE = "Обнолвленный тестовый комментарий";

    @Autowired
    private CommentRepositoryJpa commentRepository;


    @Test
    @DisplayName("Тест добавления комментария к книге")
    void testAddComment(){
       var comment = new Comment();
       comment.setBookId(BOOK_ID);
       comment.setTitle(TITLE_COMMENT);
       var savedComment = commentRepositoryJpa.save(comment);
       var commentId = savedComment.getId();
       var foundComment = commentRepositoryJpa.findById(commentId);
       Assertions.assertEquals(TITLE_COMMENT, foundComment.get().getTitle());
       var listOfComments = commentRepositoryJpa.findByBookId(BOOK_ID);
       Assertions.assertTrue(listOfComments.contains(savedComment));
    }
    @Test
    @DisplayName("Тест сохранения и удаления комментария к книге")
    void deleteComment(){
        var comment = new Comment();
        comment.setBookId(BOOK_ID);
        comment.setTitle(TITLE_COMMENT);
        var savedComment = commentRepositoryJpa.save(comment);
        var commentId = savedComment.getId();
        var foundComment = commentRepositoryJpa.findById(commentId);
        Assertions.assertEquals(TITLE_COMMENT, foundComment.get().getTitle());
        var listOfComments = commentRepositoryJpa.findByBookId(BOOK_ID);
        Assertions.assertTrue(listOfComments.contains(savedComment));
        commentRepositoryJpa.delete(commentId);
        em.detach(savedComment);
        listOfComments = commentRepositoryJpa.findByBookId(BOOK_ID);
        Assertions.assertFalse(listOfComments.contains(savedComment));
    }
    @Test
    @DisplayName("Тест обновления комментария к книге")
    void testUpdateComment(){
        var comment = new Comment();
        comment.setBookId(BOOK_ID);
        comment.setTitle(TITLE_COMMENT);
        var savedComment = commentRepositoryJpa.save(comment);
        var commentId = savedComment.getId();
        commentRepositoryJpa.updateCommentById(commentId,TITLE_COMMENT_UPDATE);
        em.detach(savedComment);
        var updatedComment = commentRepositoryJpa.findById(commentId).get();
        Assertions.assertEquals(TITLE_COMMENT_UPDATE,updatedComment.getTitle());
    }
}