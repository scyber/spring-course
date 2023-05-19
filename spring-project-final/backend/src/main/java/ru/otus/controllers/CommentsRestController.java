package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import reactor.core.publisher.Flux;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.otus.domain.Comment;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CommentsRestController {

    private final CommentRepository commentRepository;
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/comments")
    public Flux<Comment> getAllComments() {
        return this.commentRepository.findAll();
    }
    
    @RequestMapping(method = RequestMethod.GET, value = "/api/comments/{bookId}")
    public Flux<Comment> getCommentsByBookId(@PathVariable("bookId") String bookId ){
    	return this.commentRepository.findCommentsByBookId(bookId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/comments/{bookId}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<Comment> saveComment(@PathVariable("bookId") String bookId, @RequestBody Comment comment){
        var title = comment.getTitle();
        return  this.commentRepository.addCommentByBookId(bookId,title);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/api/comment/{commentId}")
    public Mono<Void> deleteComment(@PathVariable("commentId") String commentId){
        return this.commentRepository.deleteById(commentId);
    }

}
