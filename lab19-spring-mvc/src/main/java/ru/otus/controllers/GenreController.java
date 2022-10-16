package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.otus.domain.Genre;
import ru.otus.services.BookService;

import java.util.List;

@RestController
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
    @GetMapping("/api/genres")
    public List<Genre> getGenres(){
      return bookService.getAllGenres();
    }
}
