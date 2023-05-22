package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Genre;
import ru.otus.services.LibraryClientService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ClientGenresRestController {

    private final LibraryClientService libraryClientService;
    private final static Logger LOG = LoggerFactory.getLogger(ClientBookRestController.class);

    @RequestMapping(method = RequestMethod.GET, value = "/api/client/genres")
    public List<Genre> getGenres() {
        return libraryClientService.getAllGenres();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/client/genres", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Genre saveAndUpdateGenre(@RequestBody Genre genre) {
        return libraryClientService.saveAndUpdateGenre(genre);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/client/genres/{id}")
    public Genre getGenreById(@PathVariable("id") String id) {
        return libraryClientService.getGenreById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/client/genres/{genreId}")
    public void deleteGenre(@PathVariable("genreId") String genreId) {
        libraryClientService.deleteGenreById(genreId);
    }
}
