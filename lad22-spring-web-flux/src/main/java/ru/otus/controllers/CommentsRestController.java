package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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
    public Flux<Comment> getAllComments() {
        return libraryService.getAllComments();
    }

    @PostMapping("/api/comments/")
    public Mono<Comment> saveComment(@ModelAttribute("book") BookDto bookDto, @RequestParam("title") String title,
                                               BindingResult bindingResult, Model model) {
       
    	return libraryService.addComment(bookDto.toDomainObject(), title);
    }
}
