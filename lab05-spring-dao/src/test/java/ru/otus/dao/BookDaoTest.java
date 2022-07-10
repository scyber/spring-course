package ru.otus.dao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.stream.Collectors;

@JdbcTest
@Import({BookDaoJdbc.class, GenreDaoJdbc.class, AuthorDaoJdbc.class})
public class BookDaoTest {
    private static final String BOOK_NAME = "Приключения Васи Пупкина";
    private static final Long GENRE_ID = 5L;
    private static final Long AUTHOR_ID = 4L;
    private static final String BOOK_NAME_DEL = "Книга на удаление";

    @Autowired
    private BookDaoJdbc bookDao;
    @Autowired
    private GenreDaoJdbc genreDao;
    @Autowired
    private AuthorDaoJdbc authorDao;


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
		Book savedBook = bookDao.findById(bookId).orElseThrow();
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
