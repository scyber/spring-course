package ru.otus.services;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import ru.otus.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class BookServiceTest {
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;
    @Autowired
    private BookService bookService;

    @Autowired
    private GenreConverter genreConverter;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private AuthorConverter authorConverter;
    private static final String BOOK_ID = "1L";
    private static final String AUTHOR_ID = "1L";
    private static final String GENRE_ID = "1L";

    private static final String AUTHOR_NAME = "Тестовый Автор Сервиса";
    private static final String BOOK_NAME = "Тестовая книга Сервиса";
    private static final String BOOK_NAME_FOR_UPDATE = "Update Book Name";
    private static final String GENRE_NAME = "Тестовый жанр Сервиса";

    private static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.9");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }

    @BeforeAll
    static void setUp() {
        mongoDBContainer.start();
    }

    @AfterAll
    static void clean() {
        mongoDBContainer.stop();
    }

    @Test
    @DisplayName("Тестирование добавление Авторов")
    void testAuthorAdd() {
        bookService.addAuthor(AUTHOR_NAME);
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Mockito.verify(authorRepository).save(author);
    }

    @Test
    @DisplayName("Тестирование получения всех авторов")
    void testGetAllAuthors() {
        bookService.getAllAuthors();
        Mockito.verify(authorRepository).findAll();
    }

    @Test
    @DisplayName("Тестирование получения всех жанров")
    void testGetAllGenres() {
        bookService.getAllGenres();
        Mockito.verify(genreRepository).findAll();
    }

    @Test
    @DisplayName("Тестирование вызовов сервиса поиска всех книг библиотеки")
    void testFindAllBooks() {
        var books = new ArrayList<Book>();
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        bookService.getAllBooks();
        Mockito.verify(bookRepository).findAll();
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
        Mockito.when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(retBook));
        retBook.setAuthors(List.of(author));
        retBook.setGenres(List.of(genre));
        bookService.getBookById(BOOK_ID);
        Mockito.verify(bookRepository).findById(BOOK_ID);
    }

    @Test
    @DisplayName("Тестирование удаления книги")
    void testDeleteBook() {
        bookService.deleteBook(BOOK_ID);
        Mockito.verify(bookRepository).deleteById(BOOK_ID);
    }

    @Test
    @DisplayName("Тестирование добавления книги")
    void testAddBookByName() {
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        Mockito.when(genreRepository.findById(GENRE_ID)).thenReturn(Optional.of(genre));
        Mockito.when(authorRepository.findById(AUTHOR_ID)).thenReturn(Optional.of(author));
        var book = new Book();
        book.setTitle(BOOK_NAME);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        bookService.addBook(BOOK_NAME, AUTHOR_ID, GENRE_ID);
        Mockito.verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("Тестирование обновления названия книги по идентификатору")
    void testUpdateBookById() {
        bookService.updateBookNameById(BOOK_ID, BOOK_NAME_FOR_UPDATE);
        Mockito.verify(bookRepository).updateBookTitleById(BOOK_ID, BOOK_NAME_FOR_UPDATE);
    }
}