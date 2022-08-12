package ru.otus.controllers;

import lombok.RequiredArgsConstructor;
import org.hibernate.hql.spi.id.local.LocalTemporaryTableBulkIdStrategy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.otus.domain.Author;
import ru.otus.domain.AuthorsMapper;
import ru.otus.domain.Book;
import ru.otus.dto.BookDto;
import ru.otus.exeptions.FindItemExecption;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.services.BookService;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class BooksController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
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
        List<Author> authors = authorRepository.findAll();
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
        bookService.updateBookNameById(objBook.getId(),objBook.getTitle());
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam("id") Long id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> new FindItemExecption("book not found with id " + id));
        List<Author> authors = bookService.getAllAuthors();
        var authorsMapper = new AuthorsMapper();
        model.addAttribute("authorsMapper", authorsMapper);
        model.addAttribute("book", book);
        model.addAttribute("authors",authors);
        return "edit";
    }
    @PostMapping("/addauthor")
    public String addAuthorToBook(Model model, @RequestParam("bookId") Long bookId, @RequestParam("authorId") Long authorId){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var authors = book.getAuthors();
        var author = authorRepository.findById(authorId).orElseThrow(() -> new FindItemExecption("Could not find Author with id" + authorId));
        authors.add(author);
        book.setAuthors(authors);
        bookRepository.save(book);
        return "redirect:/list";
    }
    @PostMapping("/deleteauthor")
    public String deleteAuthorFromBook(Model model, @RequestParam("bookId") Long bookId, @RequestParam("authorId") Long authorId){
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var authors = book.getAuthors();
        var author = authorRepository.findById(authorId).orElseThrow(() -> new FindItemExecption("Could not find Author with id" + authorId));
        authors.remove(author);
        bookRepository.save(book);
        return "redirect:/list";
    }

}
