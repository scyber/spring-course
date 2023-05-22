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
public class CommentsRestController {

    private final LibraryClientService libraryClientService;
    private final static Logger LOGGER = LoggerFactory.getLogger(ClientLibraryRestController.class);


    @RequestMapping(method = RequestMethod.POST, value = "/client/api/comments/{bookId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Comment addCommentByBookId(@PathVariable("bookId") String bookId, @RequestBody Comment comment) {
        LOGGER.info("comment title  " + comment.getTitle());
        return libraryClientService.addCommentByBookId(bookId, comment);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/client/api/comment/{commentId}")
    public void deleteCommentById(@PathVariable("commentId") String commentId) {
        libraryClientService.deleteCommentById(commentId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/client/api/comments")
    public List<Comment> getComments() {
        return libraryClientService.getAllComments();
    }
}
