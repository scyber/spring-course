package ru.otus.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import ru.otus.clients.LibraryClient;
import ru.otus.domain.Book;
import java.util.UUID;



@SpringBootTest
class LibraryClientServiceTest {

    private static final String BOOK_ID = UUID.randomUUID().toString();
    private static final String BOOK_TITLE = "TEST_TITLE";

    @MockBean
    private LibraryClient libraryClient;

    @Autowired
    private LibraryClientService libraryClientService;

    @Test
    void getPagebleBooks() {
        libraryClientService.getPagebleBooks(1, 2);
        Mockito.verify(libraryClient).getBooks(PageRequest.of(1,2));
    }

    @Test
    void getBookById() {
        libraryClientService.getBookById(BOOK_ID);
        Mockito.verify(libraryClient).getBookById(BOOK_ID);
    }

    @Test
    void saveAndUpdateBook() {
        var book = new Book();
        libraryClientService.saveAndUpdateBook(book);
        Mockito.verify(libraryClient).saveAndUpdateBook(book);
    }

    @Test
    void deleteBookById() {
        libraryClientService.deleteBookById(BOOK_ID);
        Mockito.verify(libraryClient).deleteBookById(BOOK_ID);
    }

    @Test
    void updateBookTitleById() {
        libraryClientService.updateBookTitleById(BOOK_ID,BOOK_TITLE);
        Mockito.verify(libraryClient).updateBookTitleById(BOOK_ID,BOOK_TITLE);
    }

    @Test
    void getAllAuthors() {
        libraryClientService.getAllAuthors();
        Mockito.verify(libraryClient).getAllAuthors();
    }

    @Test
    void getAllGenres() {
        libraryClientService.getAllGenres();
        Mockito.verify(libraryClient).getAllGenres();
    }

    @Test
    void getAllComments() {
        libraryClientService.getAllComments();
        Mockito.verify(libraryClient).getAllComments();
    }
}