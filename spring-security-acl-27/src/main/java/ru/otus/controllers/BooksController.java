package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.*;
import ru.otus.dto.BookDto;
import ru.otus.services.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BooksController {
    private final BookService bookService;


    @Validated
    @PostMapping("/edit")
    public String saveBook(@Valid @ModelAttribute("book") BookDto book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit";
        }
        var objBook = book.toDomainObject();
        bookService.updateBookNameById(objBook.getId(), objBook.getTitle());
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam("id") Long id) {
        Book book = bookService.getBookById(id);
        List<Author> authors = bookService.getAllAuthors();
        var genres = bookService.getAllGenres();
        var authorsMapper = new AuthorsMapper();
        var genreesMapper = new GenresMapper();
        model.addAttribute("authorsMapper", authorsMapper);
        model.addAttribute("genresMapper", genreesMapper);
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        return "edit";
    }

    @GetMapping("/editBooks")
    public String editBookList(Model model, @RequestParam("page") Optional<Integer> page,
                               @RequestParam("size") Optional<Integer> size) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(3);
        Page<Book> bookPage = bookService.findPage(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("bookPage", bookPage);
        int totalPages = bookPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "editBooks";
    }

    @PostMapping("/deleteBook")
    public String deleteBook(Model model, @RequestParam("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/editBooks";
    }

    @GetMapping("/addBook")
    public String getAddBook(Model model) {
        var authors = bookService.getAllAuthors();
        var genres = bookService.getAllGenres();
        var bookMapper = new BookMapper();
        model.addAttribute("authors", authors);
        model.addAttribute("genres", genres);
        model.addAttribute("bookMapper", bookMapper);
        return "addBook";
    }

    @PostMapping("/addBook")
    public String postAddBook(Model model, @RequestParam("title") String title,
                              @RequestParam("authorId") Long authorId, @RequestParam("genreId") Long genreId) {
        bookService.addBook(title, authorId, genreId);
        return "redirect:/editBooks";
    }


}
