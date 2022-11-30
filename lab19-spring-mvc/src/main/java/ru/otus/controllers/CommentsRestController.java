package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.otus.domain.Comment;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.services.BookService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentsRestController {

    private final CommentRepository commentRepository;
    private final BookRepository bookRepository;
    private final BookService bookService;

    @GetMapping("/api/comments")
    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }

    @PostMapping("/api/comments/")
    public ResponseEntity<Comment> saveComment(@RequestParam("bookId") Long bookId, @RequestParam("title") String title) {
        var comment = new Comment();
        var book = bookRepository.findById(bookId).orElseThrow(() -> new NoSuchElementException("Could not find book with Id " + bookId));
        comment.setBook(book);
        comment.setTitle(title);
        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    }
}
