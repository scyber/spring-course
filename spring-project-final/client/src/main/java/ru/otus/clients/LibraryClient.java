package ru.otus.clients;


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

    @RequestMapping(method = RequestMethod.GET, value = "/books/{id}")
    Optional<Book> getBookById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/books", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Book saveAndUpdateBook(@RequestBody Book book);

    @RequestMapping(method = RequestMethod.DELETE, value = "/books/{id}")
    void deleteBookById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/books")
    Book updateBookTitleById(@PathVariable("id") String id, @RequestParam("title") String title);

    @RequestMapping (method = RequestMethod.GET, value = "/authors")
    List<Author> getAllAuthors();

    @RequestMapping(method = RequestMethod.GET, value = "/genres")
    List<Genre> getAllGenres();

    @RequestMapping(method = RequestMethod.GET, value = "/comments")
    List<Comment> getAllComments();

    @RequestMapping(method = RequestMethod.GET, value = "/genres/{id}")
    Genre getGenreById(@PathVariable("id") String id);

    @RequestMapping( method = RequestMethod.POST, value = "/genres", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Genre saveAndUpdateGenre(@RequestBody Genre genre);

    @RequestMapping(method = RequestMethod.DELETE, value = "/genres/{id}")
    void deleteGenreById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/authors", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Author saveAndUpdateAuthor(@RequestBody Author author);

    @RequestMapping(method = RequestMethod.GET, value = "/authors/{id}")
    Author getAuthorById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/authors/{id}")
    void deleteAuthorById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/comments/{bookId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    Comment addComment(@PathVariable("bookId") String bookId, @RequestBody Comment comment);

    @RequestMapping(method = RequestMethod.DELETE, value = "/comment/{commentId}")
    void deleteComment(@PathVariable("commentId") String commentId);
}
