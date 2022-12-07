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
import ru.otus.services.LibraryService;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentsRestController {

    private final LibraryService libraryService;

    @GetMapping("/api/comments")
    public List<Comment> getAllComments(){
        return libraryService.getAllComments();
    }

    @PostMapping("/api/comments/")
    public ResponseEntity<Comment> saveComment(@RequestParam("bookId") Long bookId, @RequestParam("title") String title) {
        return new ResponseEntity<>(libraryService.addComment(bookId, title), HttpStatus.OK);
    }
}
