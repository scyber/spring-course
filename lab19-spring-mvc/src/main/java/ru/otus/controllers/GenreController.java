package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.services.BookService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class GenreController {
    private final BookService bookService;

//    @PostMapping("/addGenreForBook")
//    public ModelAndView addGenreToBook(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("genreId") Long genreId) {
//        bookService.addGenreForBook(bookId, genreId);
//        model.addAttribute("id", bookId);
//        return new ModelAndView("redirect:/edit", model);
//    }
//
//    @PostMapping("/deleteGenreFromBook")
//    public ModelAndView deleteGenreFromBook(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("genreId") Long genreId) {
//        bookService.deleteGenreFromBook(bookId, genreId);
//        model.addAttribute("id", bookId);
//        return new ModelAndView("redirect:/edit", model);
//    }
}
