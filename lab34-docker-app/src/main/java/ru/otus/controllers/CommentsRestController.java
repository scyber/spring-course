package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Comment;
import ru.otus.dto.BookDto;
import ru.otus.services.LibraryService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentsRestController {

    private final LibraryService libraryService;

    @GetMapping("/api/comments")
    public List<Comment> getAllComments() {
        return libraryService.getAllComments();
    }

    @PostMapping("/api/comments/")
    public ResponseEntity<Comment> saveComment(@ModelAttribute("book") BookDto bookDto, @RequestParam("title") String title,
                                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(libraryService.addComment(bookDto.toDomainObject(), title), HttpStatus.OK);
        }
    }
}
