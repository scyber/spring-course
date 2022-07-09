package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.services.IOServiceStreams;
import java.util.stream.Collectors;

@SpringBootTest
@DisplayName("Тест Dao с книгами")
@Import({BookDao.class,GenreDao.class,AuthorDao.class})
public class BookDaoTest {
    private static final String BOOK_NAME = "Приключения Васи Пупкина";
    private static final Long GENRE_ID = 5L;
    private static final Long AUTHOR_ID = 4L;
    private static final String BOOK_NAME_DEL = "Книга на удаление";

    @Autowired
    private BookDao bookDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private AuthorDao authorDao;


    @Test
    @DisplayName("Проверка сохранения книги в библиотеку")
    void testBookDaoSave(){
		Book book = new Book();
        Genre genre = new Genre();
        genre.setId(GENRE_ID);
        Author author = new Author();
        author.setId(AUTHOR_ID);
        book.setAuthor(author);
        book.setGenre(genre);
        book.setName(BOOK_NAME);
        long bookId = bookDao.save(book);
		Book savedBook = bookDao.findById(bookId);
		Assertions.assertEquals(book.getName(),savedBook.getName());
    }
    @Test
    @DisplayName("Проверка удаления удаления книги")
    void testDeleteBook(){
        Book book = new Book();
        book.setName(BOOK_NAME_DEL);
        Author author = new Author();
        author.setId(AUTHOR_ID);
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setId(GENRE_ID);
        book.setGenre(genre);
        long bookId = bookDao.save(book);
        bookDao.delete(bookId);
        Assertions.assertFalse(bookDao.findAll().stream().map(b -> b.getId()).collect(Collectors.toList()).contains(bookId));
    }

}
