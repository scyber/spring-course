package ru.otus.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import ru.otus.domain.Book;
import java.util.UUID;


@SpringBootTest
class LibraryServiceClientServiceTest {

    private static final String BOOK_ID = UUID.randomUUID().toString();
    private static final String BOOK_TITLE = "TEST_TITLE";

    @MockBean
    private LibraryServiceClient libraryServiceClient;

    @Autowired
    private LibraryClientService libraryClientService;

    @Test
    void getPagebleBooks() {
        libraryClientService.getPagebleBooks(1, 2);
        Mockito.verify(libraryServiceClient).getBooks(PageRequest.of(1, 2));
    }

    @Test
    void getBookById() {
        libraryClientService.getBookById(BOOK_ID);
        Mockito.verify(libraryServiceClient).getBookById(BOOK_ID);
    }

    @Test
    void saveAndUpdateBook() {
        var book = new Book();
        libraryClientService.saveAndUpdateBook(book);
        Mockito.verify(libraryServiceClient).saveAndUpdateBook(book);
    }

    @Test
    void deleteBookById() {
        libraryClientService.deleteBookById(BOOK_ID);
        Mockito.verify(libraryServiceClient).deleteBookById(BOOK_ID);
    }

    @Test
    void updateBookTitleById() {
        libraryClientService.updateBookTitleById(BOOK_ID, BOOK_TITLE);
        Mockito.verify(libraryServiceClient).updateBookTitleById(BOOK_ID, BOOK_TITLE);
    }

    @Test
    void getAllAuthors() {
        libraryClientService.getAllAuthors();
        Mockito.verify(libraryServiceClient).getAllAuthors();
    }

    @Test
    void getAllGenres() {
        libraryClientService.getAllGenres();
        Mockito.verify(libraryServiceClient).getAllGenres();
    }

    @Test
    void getAllComments() {
        libraryClientService.getAllComments();
        Mockito.verify(libraryServiceClient).getAllComments();
    }
}