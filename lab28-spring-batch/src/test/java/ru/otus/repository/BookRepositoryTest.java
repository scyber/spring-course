package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@Disabled
class BookRepositoryTest {

    private final static String BOOK_NAME = "ТЕСТОВАЯ КНИГА";
    private final static String AUTHOR_NAME = "ТЕСТОВЫЙ АВТОР";
    private final static String GENRE_NAME = "Тестовый жанр";
    private final static String BOOK_NAME_TO_UPDATE = "Тестовая книга на обновление";
    private final static String BOOK_NAME_IN_REPO = "Russian modern history";

    @Autowired
    private TestEntityManager em;

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("Тест сохранения и поиска книги")
    void testSaveBook(){
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        Book book = new Book();
        book.setTitle(BOOK_NAME);
        book.setGenres(List.of(genre));
        book.setAuthors(List.of(author));
        var savedBook = bookRepository.save(book);
        assertNotNull(savedBook);
    }

    @Test
    @DisplayName("Тест получения сохранения и получения книг")
    void testGetAllBooks(){
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        Book book = new Book();
        book.setTitle(BOOK_NAME);
        book.setGenres(List.of(genre));
        book.setAuthors(List.of(author));
        var savedBook = bookRepository.save(book);
        var books = bookRepository.findAll();
        Assertions.assertTrue(books.contains(savedBook));
    }
    @Test
    @DisplayName("Тест обновления названия книги по Id")
    void tetUpdateNameById(){
        var book = bookRepository.findByTitle(BOOK_NAME_IN_REPO).get(0);
        var id = book.getId();
        em.detach(book);
        bookRepository.updateBookTitleById(id, BOOK_NAME_TO_UPDATE);
        var updatedBook = bookRepository.findById(id).get();
        Assertions.assertEquals(BOOK_NAME_TO_UPDATE, updatedBook.getTitle());
    }
    @Test
    @DisplayName("Тест удаления книги")
    void testDeleteBook(){
        var book = new Book();
        book.setTitle(BOOK_NAME);
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        var savedBook = bookRepository.save(book);
        var id = savedBook.getId();
        bookRepository.delete(savedBook);
        var books = bookRepository.findAll();
        Assertions.assertFalse(books.contains(savedBook));
    }
}