package ru.otus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;
import ru.otus.services.IOService;

@SpringBootTest
class LibraryApplicationTests {

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
	private IOService ioService;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private BookDao bookDao;

	private static final String BOOK_NAME = "The Old Man and The Sea";
	private static final String GENRE_NAME = "Drama";
	private static final Long BOOK_ID = 1L;
	private static final Long GENRE_ID = 1L;
	private static final Long AUTHOR_ID = 1L;

	@Test
	@DisplayName("Общая проверка контекста")
	void bookDaoTest(){

	}
	@Test
	@DisplayName("Проверка вызовов BookDao")
	void testBookDaoGet(){
		Assertions.assertEquals(BOOK_NAME, bookDao.findById(BOOK_ID).getName());
		Assertions.assertEquals(GENRE_ID, bookDao.findById(BOOK_ID).getGenreId());
		Assertions.assertEquals(AUTHOR_ID, bookDao.findById(BOOK_ID).getAuthorId());
	}
	@Test
	@DisplayName("Проверка сохранения книги в библиотеку")
	@Transactional
	void testBookDaoSave(){
		String bookName = "Приключения Васи Пупкина";
		Long bookGenreId = 2L;
		Long bookAuthorId = 1L;
		Book book = new Book();
		book.setGenreId(bookGenreId);
		book.setName(bookName);
		book.setAuthorId(bookAuthorId);
		Long savedId = bookDao.save(book);
		Book savedBook = bookDao.findById(savedId);
		Assertions.assertEquals(book.getName(),savedBook.getName());
	}
	@Test
	@DisplayName("Проверка удаления сохранения и удаления книги")
	@Transactional
	void testDeleteBook(){
		String bookName = "Приключения Васи Пупкина";
		Long bookGenreId = 2L;
		Long bookAuthorId = 1L;
		Book book = new Book();
		book.setGenreId(bookGenreId);
		book.setName(bookName);
		book.setAuthorId(bookAuthorId);
		Long savedId = bookDao.save(book);
		Book savedBook = bookDao.findById(savedId);
		Assertions.assertEquals(book.getName(),savedBook.getName());
		bookDao.delete(savedId);
	}


}
