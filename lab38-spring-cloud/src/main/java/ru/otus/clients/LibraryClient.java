package ru.otus.clients;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.config.LibraryLoadBalancingConfiguration;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "library-service", url = "${feign.url}" )
@LoadBalancerClient(name = "library-service", configuration = LibraryLoadBalancingConfiguration.class)
public interface LibraryClient {


    @RequestMapping(method = RequestMethod.GET, value = "/books")
    Page<Book> getBooks(Pageable pageable);

    @RequestMapping(method = RequestMethod.GET, value = "/book")
    Optional<Book> getBookById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Book saveAndUpdateBook(@RequestBody Book book);

    @RequestMapping(method = RequestMethod.DELETE, value = "/books")
    void deleteBookById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    void updateBookTitleById(@RequestParam("id") Long id, @RequestParam("title") String title);


    @RequestMapping (method = RequestMethod.GET, name = "/authors")
    List<Author> getAllAuthors();

    @RequestMapping(method = RequestMethod.GET, name = "/genres")
    List<Genre> getAllGenres();

    @RequestMapping(method = RequestMethod.GET, name = "/comments")
    List<Comment> getAllComments();

}
