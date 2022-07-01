package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;

@JdbcTest
@DisplayName("Тест Dao с книгами")
@Import({BookDao.class,GenreDao.class,AuthorDao.class})
public class BookDaoTest {
    private static final String AUTHOR_FIRST_NAME = "Ernest";
    private static final String AUTHOR_LAST_NAME = "Hemingway";
    private static final String BOOK_NAME = "The Old Man and The Sea";
    private static final String GENRE_NAME = "Drama";
    private static final Long BOOK_ID = 1L;
    private static final Long GENRE_ID = 1L;
    private static final Long AUTHOR_ID = 1L;

    @Autowired
    BookDao bookDao;
    @Autowired
    GenreDao genreDao;
    @Autowired
    AuthorDao authorDao;


    @Test
    @DisplayName("Проверка вызовов BookDao")
    void testDaoGet(){
        Assertions.assertEquals(BOOK_NAME, bookDao.findById(BOOK_ID).getName());
        Assertions.assertEquals(GENRE_ID, bookDao.findById(BOOK_ID).getGenreId());
        Assertions.assertEquals(AUTHOR_ID, bookDao.findById(BOOK_ID).getAuthorId());
    }

}
