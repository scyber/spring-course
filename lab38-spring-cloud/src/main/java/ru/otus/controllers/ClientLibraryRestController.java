package ru.otus.controllers;


import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.services.LibraryClientService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ClientLibraryRestController {

    private final LibraryClientService libraryClientService;

    @HystrixCommand(fallbackMethod = "failGetAllBooks")
    @RequestMapping(method = RequestMethod.GET, value = "/client/api/books")
    Page<Book> getBooks(@RequestParam("page") Integer page, @RequestParam("size") Integer size){
        return libraryClientService.getPagebleBooks(page,size);
    }

    @HystrixCommand
    @RequestMapping(method = RequestMethod.GET, value = "/client/api/book")
    Optional<Book> getBookById(Long id){
        return libraryClientService.getBookById(id);
    }

    @HystrixCommand
    @RequestMapping(method = RequestMethod.POST, value = "/client/api/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Book saveAndUpdateBook(@RequestBody Book book){
        return libraryClientService.saveAndUpdateBook(book);
    }

    @HystrixCommand
    @RequestMapping(method = RequestMethod.DELETE, value = "/client/api/books")
    void deleteBookById(Long id){
        libraryClientService.deleteBookById(id);
    }

    @HystrixCommand
    @RequestMapping(method = RequestMethod.POST, value = "/client/api/books")
    void updateBookTitleById( Long id, String title){
        libraryClientService.updateBookTitleById(id,title);
    }

    @HystrixCommand
    @RequestMapping(method = RequestMethod.GET, value = "/client/api/authors")
    public List<Author> getAuthors() {
        return libraryClientService.getAllAuthors();
    }

    @HystrixCommand
    @RequestMapping(method = RequestMethod.GET, value = "/client/api/genres")
    public List<Genre> getGenres(){
        return libraryClientService.getAllGenres();
    }

    @HystrixCommand
    @RequestMapping(method = RequestMethod.GET, value = "/client/api/comments")
    public List<Comment> getComments(){
        return libraryClientService.getAllComments();
    }


    private Page<Book> failGetAllBooks(Integer page, Integer size){
        var emptyList = new ArrayList<Book>();
        var emptyBookPage = new PageImpl<>(emptyList);
        return emptyBookPage;
    }

}
