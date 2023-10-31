package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Comment;
import ru.otus.services.LibraryClientService;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(originPatterns = "*")
public class ClientCommentsRestController {

    private final LibraryClientService libraryClientService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientBookRestController.class);


    @RequestMapping(method = RequestMethod.POST, value = "/api/client/comments/{bookId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Comment addCommentByBookId(@PathVariable("bookId") String bookId, @RequestBody Comment comment) {
        LOGGER.info("comment title  " + comment.getTitle());
        return libraryClientService.addCommentByBookId(bookId, comment);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/client/comment/{commentId}")
    public void deleteCommentById(@PathVariable("commentId") String commentId) {
        libraryClientService.deleteCommentById(commentId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/client/comments")
    public List<Comment> getComments() {
        return libraryClientService.getAllComments();
    }
}
