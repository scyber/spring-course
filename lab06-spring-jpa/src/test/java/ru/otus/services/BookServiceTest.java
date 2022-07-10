package ru.otus.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;

import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepositoryJpa;
import ru.otus.repository.BookRepositoryJpa;
import ru.otus.repository.GenreRepositoryJpa;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookRepositoryJpa bookRepositoryJpa;
    @Autowired
    private GenreRepositoryJpa genreRepositoryJpa;
    @Autowired
    private AuthorRepositoryJpa authorRepository;
    @Autowired
    private GenreConverter genreConverter;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private AuthorConverter authorConverter;

    @Autowired
    private BookService bookService;

    private static final String AUTHOR_NAME = "Тестовый Автор Сервиса";
    private static final String BOOK_NAME = "Тестовая книга Сервиса";
    private static final String GENRE_NAME = "Тестовый жанр Сервиса";



    @Test
    @DisplayName("Тестирование вызовов Авторов")
    @Transactional
    void testAuthorCalls(){
        Author authorFromService = bookService.addAuthor(AUTHOR_NAME);
        Author authorNameFromDao = authorRepository.findById(authorFromService.getId()).get();
        assertEquals(AUTHOR_NAME, authorNameFromDao);
        bookService.delAuthor(authorFromService.getId());
        assertFalse(bookService.getAllAuthors().contains(AUTHOR_NAME));
    }

    @Test
    @DisplayName("Тестирование вызова жанров")
    @Transactional
    void testGenreCalls(){
        long genreFromService = bookService.addGenre(GENRE_NAME).getId();
        String genreFromDao = genreRepositoryJpa.findById(genreFromService).get().getName();
        assertEquals(GENRE_NAME, genreFromDao);
        bookService.delGenre(genreFromService);
        assertFalse(bookService.getAllGenres().contains(GENRE_NAME));
    }
    @Test
    @DisplayName("Тестирование вызовов сервиса книги в комплексе")
    @Transactional
    void testBookCalls(){
        long authorId = bookService.addAuthor(AUTHOR_NAME).getId();
        long genreId = bookService.addGenre(GENRE_NAME).getId();
        long bookId = bookService.addBook(BOOK_NAME,authorId,genreId);
        assertEquals(bookId, bookRepositoryJpa.findById(bookId));
        bookService.delBook(bookId);
        assertFalse(bookService.getAllBooks().contains(bookId));
    }
}