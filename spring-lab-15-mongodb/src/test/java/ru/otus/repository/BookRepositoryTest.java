package ru.otus.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.MongoDBContainer;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Genre;
import java.util.List;
import java.util.Optional;


@DataMongoTest
@ExtendWith(SpringExtension.class)
@ImportAutoConfiguration(exclude = EmbeddedMongoAutoConfiguration.class)
class BookRepositoryTest {

    private final static String BOOK_TITLE = "ТЕСТОВАЯ КНИГА";
    private final static String AUTHOR_NAME = "ТЕСТОВЫЙ АВТОР";
    private final static String GENRE_NAME = "Тестовый жанр";
    private final static String BOOK_TITLE_TO_UPDATE = "Тестовая книга на обновление";
    private final static String BOOK_NAME_IN_REPO = "Russian modern history";


    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private GenreRepository genreRepository;

    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:5.0.9");

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
    @DisplayName("Тест сохранения и поиска книги")
    void testSaveBook() {
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        var savedGenre = genreRepository.save(genre);
        var savedAuthor = authorRepository.save(author);
        Book book = new Book();
        book.setTitle(BOOK_TITLE);
        book.setGenres(List.of(savedGenre));
        book.setAuthors(List.of(savedAuthor));
        var savedBook = bookRepository.save(book);
        Assertions.assertEquals(BOOK_TITLE, savedBook.getTitle());
    }

    @Test
    @DisplayName("Тест получения сохранения и получения книг")
    void testGetAllBooks() {
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        Book book = new Book();
        book.setTitle(BOOK_TITLE);
        var savedGenre = genreRepository.save(genre);
        var savedAuthor = authorRepository.save(author);
        book.setGenres(List.of(savedGenre));
        book.setAuthors(List.of(savedAuthor));
        var savedBook = bookRepository.save(book);
        var books = bookRepository.findAll();
        Assertions.assertTrue(books.contains(savedBook));
    }

    @Test
    @DisplayName("Тест обновления названия книги по Id")
    void testUpdateNameById() {
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        Book book = new Book();
        book.setTitle(BOOK_TITLE);
        var savedGenre = genreRepository.save(genre);
        var savedAuthor = authorRepository.save(author);
        var savedBook = bookRepository.save(book);
        String id = savedBook.getId();
        bookRepository.updateBookTitleById(id, BOOK_TITLE_TO_UPDATE);
        var updatedBook = bookRepository.findById(id).get();
        Assertions.assertEquals(BOOK_TITLE_TO_UPDATE, updatedBook.getTitle());
    }

    @Test
    @DisplayName("Тест удаления книги")
    void testDeleteBook() {
        var book = new Book();
        book.setTitle(BOOK_TITLE);
        var author = new Author();
        author.setName(AUTHOR_NAME);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        var savedBook = bookRepository.save(book);
        var id = savedBook.getId();
        bookRepository.delete(savedBook);
        var optionalBook = bookRepository.findById(id);
        Assertions.assertEquals(Optional.empty(), optionalBook);
    }
}