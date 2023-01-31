package ru.otus.repository;


import java.util.stream.Collectors;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import ru.otus.domain.Book;
import ru.otus.domain.Comment;



@DataMongoTest
@ExtendWith(SpringExtension.class)
//@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class CommentRepositoryTest {


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CommentRepository commentRepository;
    
    private static final String TITLE_COMMENT = "This is a test comment";
    private static final String TITLE_OF_BOOK = "Test Book";
    private static final String TITLE_UPD_COMMENT = "This is a updated comment";
    private static final String TITLE_INS_COMMENT = "This is a comment by bookId";

    @Test
    @DisplayName("Тест добавления комментария и поиска по bookId")
    void testAddComment(){
       var book = new Book();
       book.setTitle(TITLE_OF_BOOK);
       var savedBook = bookRepository.save(book).block();
       var bookId = savedBook.getId();
       var comment = new Comment();
       comment.setBook(savedBook);
       comment.setTitle(TITLE_COMMENT);
       var savedComment = commentRepository.save(comment).block();
       
       var comments =  commentRepository.findCommentsByBookId(bookId)
       .collect(Collectors.toList()).block();
       
       Assertions.assertTrue(comments.contains(savedComment));;

    }
    
    @Test
    @DisplayName("Тест сохранения и удаления сохраненного комментария по bookId комментария")
    void testFindComment(){
    	var book = new Book();
    	book.setTitle(TITLE_COMMENT);
        var bookSaved = bookRepository.save(book).block();
        var bookId = bookSaved.getId();
        var comment = new Comment();
        comment.setBook(bookSaved);
        comment.setTitle(TITLE_COMMENT);
        commentRepository.save(comment).block();
        commentRepository.deleteCommentsByBookId(bookId).blockLast();
        var foundComments = commentRepository.findCommentsByBookId(bookId).collect(Collectors.toList()).block();
        
        Assertions.assertTrue(foundComments.isEmpty());
    }
    
    
    @Test
    @DisplayName("Тестирование обновления комментария")
    void deleteComment(){
    	var book = new Book();
    	book.setTitle(TITLE_OF_BOOK);
    	var savedBook = bookRepository.save(book).block();
        var comment = new Comment();
        comment.setTitle(TITLE_COMMENT);
        comment.setBook(savedBook);
        var savedComment = commentRepository.save(comment).block();
        commentRepository.updateCommentById(savedComment.getId(), TITLE_UPD_COMMENT).block();
        var updatedComment = commentRepository.findById(savedComment.getId()).block();
        
        Assertions.assertTrue(updatedComment.getTitle().equals(TITLE_UPD_COMMENT));
        
    }
    
    @Test
    void addCommentByBookId() {
    	var book = new Book();
    	book.setTitle(TITLE_OF_BOOK);
    	var savedBook = bookRepository.save(book).block();
    	var bookId = savedBook.getId();
    	var comment = commentRepository.addCommentByBookId(bookId, TITLE_INS_COMMENT).block();
    	
    	Assertions.assertEquals(TITLE_INS_COMMENT, comment.getTitle());
    	
    	var comments = commentRepository.findAll().collect(Collectors.toList()).block();
    	System.out.println("-------");
    	comments.forEach(c -> System.out.println(c.getTitle()));

    }
}