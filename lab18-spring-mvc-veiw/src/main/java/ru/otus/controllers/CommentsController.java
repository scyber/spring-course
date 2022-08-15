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
import ru.otus.domain.CommentMapper;
import ru.otus.services.BookService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class CommentsController {
    private final BookService bookService;

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
}
