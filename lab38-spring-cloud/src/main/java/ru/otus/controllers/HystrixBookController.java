package ru.otus.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.clients.LibraryClient;
import ru.otus.domain.Book;
import ru.otus.services.HystrixLibraryService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class HystrixBookController {

    private final HystrixLibraryService hystrixLibraryService;
    private final LibraryClient libraryClient;

    @RequestMapping(method = RequestMethod.GET, value = "/hystrix/api/books")
    Page<Book> getBooks(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return libraryClient.getBooks(PageRequest.of(page,size));
        //return hystrixLibraryService.getPagebleBooks(page, size);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/hystrix/api/book")
    Optional<Book> getBookById(Long id){
        return hystrixLibraryService.getBookById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hystrix/api/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Book saveAndUpdateBook(@RequestBody Book book){
        return hystrixLibraryService.saveAndUpdateBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/hystrix/api/books")
    void deleteBookById(Long id){
        hystrixLibraryService.deleteBookById(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/hystrix/api/books")
    void updateBookTitleById( Long id, String title){
        hystrixLibraryService.updateBookTitleById(id,title);
    }


}
