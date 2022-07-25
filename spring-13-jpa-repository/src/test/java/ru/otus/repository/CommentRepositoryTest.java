package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class CommentRepositoryTest {

    private final static String BOOK_NAME = "ТЕСТОВАЯ КНИГА";
    private final static String AUTHOR_NAME = "ТЕСТОВЫЙ АВТОР";
    private final static String AUTHOR_NAME_FOR_UPDATE = "Update Authoer Name";
    private final static String GENRE_NAME = "Тестовый жанр";
    private final static String BOOK_NAME_TO_UPDATE = "Тестовая книга на обновление";
    private final static String BOOK_NAME_IN_REPO = "Russian modern history";

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepositoryJpa bookRepository;

    @Autowired
    private GenreRepository genreRepository;

    @Autowired
    private AuthorRepository authorRepository;

    private static final String TITLE_COMMENT = "Комментарий тестовый";


    @Autowired
    private CommentRepository commentRepository;


    @Test
    void testAddComment(){
       var book = new Book();
       book.setName(BOOK_NAME);
       var genre = new Genre();
       genre.setName(GENRE_NAME);
       var author = new Author();
       author.setName(AUTHOR_NAME);
       book.setGenre(genre);
       book.setAuthor(author);
       var savedBook = bookRepository.save(book);
       var bookId = savedBook.getId();
       var comment = new Comment();
       comment.setBook(savedBook);
       comment.setTitle(TITLE_COMMENT);
       var savedComment = commentRepository.save(comment);
       var commentId = savedComment.getId();
       var foundComment = commentRepository.findById(commentId);
       Assertions.assertEquals(TITLE_COMMENT, foundComment.get().getTitle());
       var listOfComments = commentRepository.findByBookId(bookId);
       Assertions.assertTrue(listOfComments.contains(savedComment));
    }
}