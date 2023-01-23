package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Comment;
import ru.otus.services.LibraryService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentsRestController {

    private final LibraryService libraryService;

    @GetMapping("/api/comments")
    public Flux<Comment> getAllComments() {
        return this.libraryService.getAllComments();
    }
    
    @GetMapping("/api/comment/{bookId}")
    public Flux<Comment> getCommentsByBookId(@PathVariable("bookId") String bookId ){
    	return this.libraryService.findCommentsByBookId(bookId);
    }
}
