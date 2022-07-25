package ru.otus.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({BookRepositoryJpa.class, AuthorRepository.class, GenreRepository.class})
class BookRepositoryJpaTest {

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


    @Test
    @Transactional
    @Rollback
    @DisplayName("Тест сохранения и поиска книги")
    void testSaveBook(){
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        var savedAuthor = authorRepository.save(author);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var savedGenre = genreRepository.save(genre);
        Book book = new Book();
        book.setName(BOOK_NAME);
        book.setGenre(savedGenre);
        book.setAuthor(savedAuthor);
        var savedBook = bookRepository.save(book);
        var foundBook = bookRepository.findById(savedBook.getId()).get();
        assertEquals(savedBook,foundBook);
    }

    @Test
    @Transactional
    @DisplayName("Тест получения сохранения и получения книг")
    void testGetAllBooks(){
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        var savedAuthor = authorRepository.save(author);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var savedGenre = genreRepository.save(genre);
        Book book = new Book();
        book.setName(BOOK_NAME);
        book.setGenre(savedGenre);
        book.setAuthor(savedAuthor);
        var savedBook = bookRepository.save(book);
        var books = bookRepository.findAll();
        Assertions.assertTrue(books.contains(savedBook));
    }
    @Test
    @Rollback
    @DisplayName("Тест обновления названия книги по Id")
    void tetUpdateNameById(){
        var book = bookRepository.findByName(BOOK_NAME_IN_REPO).get(0);
        var id = book.getId();
        em.detach(book);
        bookRepository.updateBookNameById(id, BOOK_NAME_TO_UPDATE);
        var updatedBook = bookRepository.findById(id).get();
        Assertions.assertEquals(BOOK_NAME_TO_UPDATE, updatedBook.getName());
    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("Тест удаления книги")
    void testDeleteBook(){
        var book = new Book();
        book.setName(BOOK_NAME);
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        book.setAuthor(author);
        book.setGenre(genre);
        var savedBook = bookRepository.save(book);
        var id = savedBook.getId();
        bookRepository.deleteById(id);
        em.detach(savedBook);
        var notFoundBook = bookRepository.findById(id);
        Assertions.assertEquals(notFoundBook, Optional.empty());
    }
}