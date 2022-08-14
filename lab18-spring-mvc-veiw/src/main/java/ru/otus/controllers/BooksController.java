package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
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

    @GetMapping("/list")
    public String listPage(Model model,
                           @RequestParam("page") Optional<Integer> page,
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
        return "list";
    }

    @GetMapping("/")
    public String indexPage(Model model) {
        return "index";
    }

    @GetMapping("/authors")
    public String authors(Model model) {
        List<Author> authors = bookService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

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

    @PostMapping("/addAuthorForBook")
    public ModelAndView addAuthorToBook(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("authorId") Long authorId) {
        bookService.addAuthorForBook(bookId, authorId);
        model.addAttribute("id", bookId);
        return new ModelAndView("redirect:/edit", model);
    }

    @PostMapping("/deleteAuthorFromBook")
    public ModelAndView deleteAuthorFromBook(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("authorId") Long authorId) {
        bookService.deleteAuthorFromBook(bookId, authorId);
        model.addAttribute("id", bookId);
        return new ModelAndView("redirect:/edit", model);
    }

    @PostMapping("/addGenreForBook")
    public ModelAndView addGenreToBook(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("genreId") Long genreId) {
        bookService.addGenreForBook(bookId, genreId);
        model.addAttribute("id", bookId);
        return new ModelAndView("redirect:/edit", model);
    }

    @PostMapping("/deleteGenreFromBook")
    public ModelAndView deleteGenreFromBook(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("genreId") Long genreId) {
        bookService.deleteGenreFromBook(bookId, genreId);
        model.addAttribute("id", bookId);
        return new ModelAndView("redirect:/edit", model);
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

    @GetMapping("/viewComments")
    public String viewComments(Model model, @RequestParam("id") Long id) {
        var comments = bookService.findCommentsByBookId(id);
        model.addAttribute("comments", comments);
        return "viewComments";
    }

    @GetMapping("/editComments")
    public String editComments(Model model, @RequestParam("id") Long id) {
        var comments = bookService.findCommentsByBookId(id);
        var commentMapper = new CommentMapper();
        model.addAttribute("commentMapper", commentMapper);
        model.addAttribute("comments", comments);
        model.addAttribute("id", id);
        return "editComments";
    }

    @PostMapping("/deleteComment")
    public ModelAndView deleteComment(ModelMap model,
                                      @RequestParam("bookId") Long bookId, @RequestParam("commentId") Long commentId) {
        var comments = bookService.findCommentsByBookId(bookId);
        model.addAttribute("id", bookId);
        model.addAttribute("comments", comments);
        bookService.deleteCommentById(commentId);
        return new ModelAndView("redirect:/editComments", model);
    }

    @PostMapping("/addComment")
    public ModelAndView addComment(ModelMap model, @RequestParam("bookId") Long bookId, @RequestParam("title") String title) {
        var book = bookService.getBookById(bookId);
        bookService.addComment(bookId, title);
        model.addAttribute("id", bookId);
        return new ModelAndView("redirect:/editComments", model);
    }


}
