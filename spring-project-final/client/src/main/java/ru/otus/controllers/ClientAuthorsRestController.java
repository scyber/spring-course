package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Author;
import ru.otus.services.LibraryServiceClient;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ClientAuthorsRestController {

    private final LibraryServiceClient libraryServiceClient;
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientBookRestController.class);


    @RequestMapping(method = RequestMethod.GET, value = "/api/client/authors")
    public List<Author> getAuthors() {
        return libraryServiceClient.getAllAuthors();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/client/authors/{authorId}")
    public Author getAuthorById(@PathVariable("authorId") String authorId) {
        return libraryServiceClient.getAuthorById(authorId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/client/authors", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Author addAuthor(@RequestBody Author author) {
        return libraryServiceClient.saveAndUpdateAuthor(author);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/client/authors/{authorId}")
    public void deleteAuthor(@PathVariable("authorId") String authorId) {
        libraryServiceClient.deleteAuthorById(authorId);
    }

}
