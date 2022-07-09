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
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;
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
        long authorFromService = bookService.addAuthor(AUTHOR_NAME);
        String authorNameFromDao = authorDao.findById(authorFromService).get().getName();
        assertEquals(AUTHOR_NAME, authorNameFromDao);
        bookService.delAuthor(authorFromService);
        assertFalse(bookService.getAllAuthors().contains(AUTHOR_NAME));
    }

    @Test
    @DisplayName("Тестирование вызова жанров")
    @Transactional
    void testGenreCalls(){
        long genreFromService = bookService.addGenre(GENRE_NAME);
        String genreFromDao = genreDao.findById(genreFromService).get().getGenreName();
        assertEquals(GENRE_NAME, genreFromDao);
        bookService.delGenre(genreFromService);
        assertFalse(bookService.getAllGenres().contains(GENRE_NAME));
    }
    @Test
    @DisplayName("Тестирование вызовов сервиса книги в комплексе")
    @Transactional
    void testBookCalls(){
        long authorId = bookService.addAuthor(AUTHOR_NAME);
        long genreId = bookService.addGenre(GENRE_NAME);
        long bookId = bookService.addBook(BOOK_NAME,authorId,genreId);
        assertEquals(bookId, bookDao.findById(bookId).getId());
        bookService.delBook(bookId);
        assertFalse(bookService.getAllBooks().contains(bookId));
    }
}