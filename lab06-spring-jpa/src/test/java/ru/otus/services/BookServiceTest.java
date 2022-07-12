package ru.otus.services;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.dao.*;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.*;

import java.util.ArrayList;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private GenreRepository genreRepository;

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
    void prepareMocks(){
        bookRepository = Mockito.mock(BookRepository.class);
        authorRepository = Mockito.mock(AuthorRepository.class);
        genreRepository = Mockito.mock(GenreRepository.class);
        bookService = new BookServiceImpl(bookRepository,authorRepository,genreRepository,
                bookConverter,genreConverter,authorConverter);
    }

    @Test
    @DisplayName("Тестирование добавление Авторов")
    void testAuthorAdd(){
        bookService.addAuthor(AUTHOR_NAME);
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Mockito.verify(authorRepository).save(author);
    }
    @Test
    @DisplayName("Тестирование получения всех авторов")
    void testGetAllAuthors(){
        bookService.getAllAuthors();
        Mockito.verify(authorRepository).findAll();
    }

    @Test
    @DisplayName("Тестирование получения всех жанров")
    void testGetAllGenres(){
        bookService.getAllGenres();
        Mockito.verify(genreRepository).findAll();
    }
    @Test
    @DisplayName("Тестирование вызовов сервиса поиска всех книг библиотеки")
    void testFindAllBooks(){
        var books = new ArrayList<Book>();
        Mockito.when(bookRepository.findAll()).thenReturn(books);
        bookService.getAllBooks();
        Mockito.verify(bookRepository).findAll();
    }
    @Test
    @DisplayName("Тестирование вызова поиска книги по ID")
    void testFindBookById(){
        Book retBook = new Book();
        retBook.setId(BOOK_ID);
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        Genre genre = new Genre();
        genre.setName(GENRE_NAME);
        Mockito.when(bookRepository.findById(BOOK_ID)).thenReturn(Optional.of(retBook));
        retBook.setAuthor(author);
        retBook.setGenre(genre);
        bookService.getBookById(BOOK_ID);
        Mockito.verify(bookRepository).findById(BOOK_ID);
    }
    @Test
    @DisplayName("Тестирование удаления книги")
    void testDeleteBook(){
        bookService.delBook(BOOK_ID);
        Mockito.verify(bookRepository).delete(BOOK_ID);
    }
    @Test
    @DisplayName("Тестирование добавления книги")
    void testAddBookByName(){
        var author = new Author();
        author.setName(AUTHOR_NAME);
        author.setId(AUTHOR_ID);
        var genre = new Genre();
        genre.setName(GENRE_NAME);
        genre.setId(GENRE_ID);
        Mockito.when(genreRepository.findById(GENRE_ID)).thenReturn(Optional.of(genre));
        Mockito.when(authorRepository.findById(AUTHOR_ID)).thenReturn(Optional.of(author));
        var book = new Book();
        book.setName(BOOK_NAME);
        book.setAuthor(author);
        book.setGenre(genre);
        bookService.addBook(BOOK_NAME,AUTHOR_ID,GENRE_ID);
        Mockito.verify(bookRepository).save(book);
    }
    @Test
    @DisplayName("Тестирование обновления названия книги по идентификатору")
    void testUpdateBookById(){
        bookService.updateBookNameById(BOOK_ID,BOOK_NAME_FOR_UPDATE);
        Mockito.verify(bookRepository).updateNameById(BOOK_ID,BOOK_NAME_FOR_UPDATE);
    }
}