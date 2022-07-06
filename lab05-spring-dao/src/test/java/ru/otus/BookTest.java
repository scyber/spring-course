package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.xmlunit.builder.Input;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.services.BookShellProcessor;
import ru.otus.services.IOService;
import ru.otus.services.IOServiceStreams;
import java.util.stream.Collectors;

@SpringBootTest
@DisplayName("Тест Dao с книгами")
@Import({BookDao.class,GenreDao.class,AuthorDao.class})
public class BookTest {
    private static final String BOOK_NAME = "Приключения Васи Пупкина";
    private static final Long GENRE_ID = 5L;
    private static final Long AUTHOR_ID = 4L;

    @Autowired
    private BookDao bookDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private AuthorDao authorDao;

    private long bookId;

    @Test
    @DisplayName("Проверка сохранения книги в библиотеку")
    @Order(1)
    void testBookDaoSave(){
		Book book = new Book();
        Genre genre = new Genre();
        genre.setId(GENRE_ID);
        Author author = new Author();
        author.setId(AUTHOR_ID);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setName(BOOK_NAME);
        bookId = bookDao.save(book);
		Book savedBook = bookDao.findById(bookId);
		Assertions.assertEquals(book.getName(),savedBook.getName());
    }
    @Test
    @DisplayName("Проверка удаления удаления книги")
    @Order(2)
    void testDeleteBook(){
		bookDao.delete(bookId);
       Assertions.assertFalse(bookDao.findAll().stream().map(book -> book.getId()).collect(Collectors.toList()).contains(bookId));
    }
    @Test
    @DisplayName("Тестирование операций с книгами")
    public void testBookOperation(){
//        Author author = new Author(1L, "TestAuthor");
//        Genre genre = new Genre(1L, "TestGenre");
//        Book book = new Book(1L, "TestBook", author,genre);
//        List<Book> books = new ArrayList<>();
//        books.add(book);
//        BookDao bookDaoMock = Mockito.mock(BookDao.class);
//        IOService ioService = Mockito.mock(IOServiceStreams.class);
//
//        BookShellProcessor shellProcessor = new BookShellProcessor(bookDao,authorDao,genreDao,bookConverter,
//                                                                  genreConverter,authorConverter,ioService );

        IOServiceStreams is = new IOServiceStreams(System.in,System.out);
        is.outputString("1");
        System.out.println();
        //long r = is.readLong();
        //is.outputString(r+"");

    }

}
