package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.domain.Author;
import ru.otus.services.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class AuthorsController {
    private final BookService bookService;

//    @PostMapping("/addAuthorForBook")
//    public ModelAndView addAuthorToBook(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("authorId") Long authorId) {
//        bookService.addAuthorForBook(bookId, authorId);
//        model.addAttribute("id", bookId);
//        return new ModelAndView("redirect:/edit", model);
//    }
//
//    @PostMapping("/deleteAuthorFromBook")
//    public ModelAndView deleteAuthorFromBook(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("authorId") Long authorId) {
//        bookService.deleteAuthorFromBook(bookId, authorId);
//        model.addAttribute("id", bookId);
//        return new ModelAndView("redirect:/edit", model);
//    }
//
//    @GetMapping("/authors")
//    public String authors(Model model) {
//        List<Author> authors = bookService.getAllAuthors();
//        model.addAttribute("authors", authors);
//        return "authors";
//    }
}
