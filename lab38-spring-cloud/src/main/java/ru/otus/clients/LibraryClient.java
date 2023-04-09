package ru.otus.clients;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Book;

import java.util.Optional;

@FeignClient("library-client")
public interface LibraryClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/books")
    Page<Book> getBooks(Pageable pageable);

    @RequestMapping(method = RequestMethod.GET, value = "/api/book")
    Optional<Book> getBookById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/api/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Book saveAndUpdateBook(@RequestBody Book book);

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/books")
    void deleteBookById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/api/books")
    void updateBookTitleById(@RequestParam("id") Long id, @RequestParam("title") String title);

}
