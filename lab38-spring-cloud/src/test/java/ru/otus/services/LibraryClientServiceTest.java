package ru.otus.services;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import ru.otus.clients.LibraryClient;
import ru.otus.domain.Book;
import java.util.Optional;


@SpringBootTest
class LibraryClientServiceTest {

    @MockBean
    LibraryClient libraryClient;

    @Autowired
    private LibraryClientService libraryClientService;

    private final Long BOOK_ID = 1L;
    private final Integer PAGE = 1;
    private final Integer SIZE = 2;
    private final String TEST_TITLE = "TEST BOOK TITLE";

    @Test
    @DisplayName("Test get All pageble Books")
    public void getPagebleBooksTest(){
        libraryClientService.getPagebleBooks(PAGE,SIZE);
        Mockito.verify(libraryClient).getBooks(PageRequest.of(PAGE,SIZE));
    }

    @Test
    @DisplayName("Get get by ID Book")
    public void getBookByIdTest(){
        var tmpBook = new Book();
        tmpBook.setTitle(TEST_TITLE);
        Mockito.when(libraryClient.getBookById(BOOK_ID)).thenReturn(Optional.of(tmpBook));
        libraryClientService.getBookById(BOOK_ID);
        Mockito.verify(libraryClient).getBookById(BOOK_ID);
    }

    @Test
    @DisplayName("Delete Book by ID")
    public void deleteBookById(){
        libraryClientService.deleteBookById(BOOK_ID);
        Mockito.verify(libraryClient).deleteBookById(BOOK_ID);
    }

    @Test
    @DisplayName("Update title book from Book Client")
    public void updateTitleBook(){
        libraryClientService.updateBookTitleById(BOOK_ID,TEST_TITLE);
        Mockito.verify(libraryClient).updateBookTitleById(BOOK_ID,TEST_TITLE);
    }

    @Test
    @DisplayName("Test save book from Book Client")
    public void testSaveBookByClient(){
        var book = new Book();
        book.setTitle(TEST_TITLE);
        libraryClientService.saveAndUpdateBook(book);
        Mockito.verify(libraryClient).saveAndUpdateBook(book);
    }


    @Test
    @DisplayName("Test getting authors from Client")
    public void testGetAuthorsFromClient(){
        libraryClientService.getAllAuthors();
        Mockito.verify(libraryClient).getAllAuthors();
    }

    @Test
    @DisplayName("Test getting genres from Client")
    public void testGetGenresFromClient(){
        libraryClientService.getAllGenres();
        Mockito.verify(libraryClient).getAllGenres();
    }

    @Test
    @DisplayName("Test getting all comments")
    public void testGettingAllComments(){
        libraryClientService.getAllComments();
        Mockito.verify(libraryClient).getAllComments();
    }
}