package ru.otus.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.dao.*;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.ArrayList;
import java.util.Optional;


@SpringBootTest
class BookServiceTest {

    @MockBean
    private BookDao bookDao;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;

    private BookService bookService;

    @Autowired
    private GenreConverter genreConverter;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private AuthorConverter authorConverter;
    private static final long BOOK_ID = 1L;
    private static final long AUTHOR_ID = 1L;
    private static final long GENRE_ID = 1L;

    private static final String AUTHOR_NAME = "Тестовый Автор Сервиса";
    private static final String BOOK_NAME = "Тестовая книга Сервиса";
    private static final String BOOK_NAME_FOR_UPDATE = "Update Book Name";
    private static final String GENRE_NAME = "Тестовый жанр Сервиса";

    @BeforeEach
    void prepareMocks() {
        bookService = new BookServiceImpl(bookDao, authorDao, genreDao,
                bookConverter, genreConverter, authorConverter);
    }

    @Test
    @DisplayName("Тестирование добавление Авторов")
    void testAuthorAdd() {
        bookService.addAuthor(AUTHOR_NAME);
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Mockito.verify(authorDao).save(author);
    }

    @Test
    @DisplayName("Тестирование получения всех авторов")
    void testGetAllAuthors() {
        bookService.getAllAuthors();
        Mockito.verify(authorDao).findAll();
    }

    @Test
    @DisplayName("Тестирование получения всех жанров")
    void testGetAllGenres() {
        bookService.getAllGenres();
        Mockito.verify(genreDao).findAll();
    }

    @Test
    @DisplayName("Тестирование вызовов сервиса поиска всех книг библиотеки")
    void testFindAllBooks() {
        var books = new ArrayList<Book>();
        Mockito.when(bookDao.findAll()).thenReturn(books);
        bookService.getAllBooks();
        Mockito.verify(bookDao).findAll();
    }

    @Test
    @DisplayName("Тестирование вызова поиска книги по ID")
    void testFindBookById() {
        Book retBook = new Book();
        retBook.setId(BOOK_ID);
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        Mockito.when(bookDao.findById(BOOK_ID)).thenReturn(Optional.of(retBook));
        retBook.setAuthor(author);
        retBook.setGenre(genre);
        bookService.getBookById(BOOK_ID);
        Mockito.verify(bookDao).findById(BOOK_ID);
    }

    @Test
    @DisplayName("Тестирование удаления книги")
    void testDeleteBook() {
        bookService.deleteBook(BOOK_ID);
        Mockito.verify(bookDao).delete(BOOK_ID);
    }

    @Test
    @DisplayName("Тестирование добавления книги")
    void testAddBookByName() {
        var author = new Author();
        author.setName(AUTHOR_NAME);
        author.setId(AUTHOR_ID);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        genre.setId(GENRE_ID);
        Mockito.when(genreDao.findById(GENRE_ID)).thenReturn(Optional.of(genre));
        Mockito.when(authorDao.findById(AUTHOR_ID)).thenReturn(Optional.of(author));
        var book = new Book();
        book.setName(BOOK_NAME);
        book.setAuthor(author);
        book.setGenre(genre);
        bookService.addBook(BOOK_NAME, AUTHOR_ID, GENRE_ID);
        Mockito.verify(bookDao).save(book);
    }

    @Test
    @DisplayName("Тестирование обновления названия книги по идентификатору")
    void testUpdateBookById() {
        bookService.updateBookNameById(BOOK_ID, BOOK_NAME_FOR_UPDATE);
        Mockito.verify(bookDao).updateNameById(BOOK_ID, BOOK_NAME_FOR_UPDATE);
    }
}