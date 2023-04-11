package ru.otus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.otus.domain.Book;
import java.util.Optional;

public interface HystrixLibraryService {

    Page<Book> getPagebleBooks(Integer page, Integer size);

    Optional<Book> getBookById(Long id);

    Book saveAndUpdateBook(Book book);

    void deleteBookById(Long id);

    void updateBookTitleById(Long id, String title);

}
