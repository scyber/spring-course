package ru.otus.controllers;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.services.BookService;
import ru.otus.services.IOService;

import java.util.List;

@ShellComponent
public class LibraryController {

    private final BookService bookService;
    private final IOService ioService;

    public LibraryController(BookService bookService, IOService ioService) {
        this.bookService = bookService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "show All Books", key = {"show_b"})
    public void showAllBooks(){
        bookService.getAllBooks().forEach(ioService::outputString);
    }

    @ShellMethod(value = "show By Id", key = {"show_b_id"})
    public void showById(){
        String bookId = ioService.readStringWithPrompt("Please enter book Id");
        ioService.outputString(bookService.getBookById(bookId));
    }

    @ShellMethod(value = "add Book", key = {"add_b"})
    public void addBook(){
        String title = ioService.readStringWithPrompt("Please enter Book name ");
        String authorId = ioService.readStringWithPrompt("Please enter author Id");
        String genreId = ioService.readStringWithPrompt("Please enter genre Id");
        var book = bookService.addBook(title,authorId,genreId);
        ioService.outputString(title + " Is created with ID "+ book.getId());
    }

    @ShellMethod(value = "delete Book", key = {"del_b"})
    public void deleteBook(){
        String bookId = ioService.readStringWithPrompt(" Please enter ID book to Delete ");
        bookService.deleteBook(bookId);
        ioService.outputString("Book with id " + bookId + " is deleted ");
    }

    @ShellMethod(value = "update Book Name By Id", key = {"upd_b_by_id"})
    public void updateNameById(){
        String bookId = ioService.readStringWithPrompt("Please enter book id to update");
        String title = ioService.readStringWithPrompt("Please enter new Book Name");
        bookService.updateBookNameById(bookId,title);
    }
    @ShellMethod(value = "show All Authors", key = {"show_a"})
    public void showAllAuthors(){
        bookService.getAllAuthors().forEach(ioService::outputString);
    }

    @ShellMethod(value = "add Author ", key = {"add_a"})
    public void addAuthor(){
        String authorName = ioService.readStringWithPrompt("Please enter Author Name");
        var author = bookService.addAuthor(authorName);
        ioService.outputString("Author with added with id " + author);
    }

    @ShellMethod(value = "delete Author", key = {"del_a"})
    public void deleteAuthor(){
        String authorId = ioService.readStringWithPrompt("Please enter Author Id");
        bookService.deleteAuthor(authorId);
        ioService.outputString("Author was deleted ");
    }

    @ShellMethod(value = "show All Genres", key = {"show_g"})
    public void showAllGenres(){
        bookService.getAllGenres().forEach(ioService::outputString);
    }

    @ShellMethod(value = "add Genre", key = {"add_g"})
    public void addGenre(){
        String genreName = ioService.readStringWithPrompt("Please enter Genre name");
        Genre genre = bookService.addGenre(genreName);
        ioService.outputString("Genre with was added with id " + genre.getId());
    }

    @ShellMethod(value = "delete Genre", key = {"del_g"})
    public void deleteGenre(){
        String genreId = ioService.readStringWithPrompt("Please provide Genre ID");
        bookService.deleteGenre(genreId);
        ioService.outputString("Genre with ID " + genreId + " is deleted ");
    }
    @ShellMethod(value = "show Comments", key = {"show_c"})
    public void showCommentsOfBook(){
        String bookId = ioService.readStringWithPrompt("Please enter book Id");
        var result = bookService.findCommentsByBookId(bookId);
        result.forEach(c -> ioService.outputString("id "+ c.getId() + " | " + c.getTitle()));
    }
    @ShellMethod(value = "add Comment", key = {"add_c"})
    public void addCommentForBook(){
        String bookId = ioService.readStringWithPrompt("Please Enter book id for comment");
        var title = ioService.readStringWithPrompt("Please enter text of your comment");
        var comment = bookService.addComment(bookId,title);
        ioService.outputString(" Your comment was added with id " + comment.getId());
    }
    @ShellMethod(value = "delete Comment", key = {"del_c"})
    public void deleteComment(){
        String commentId = ioService.readStringWithPrompt("Please enter id of comment to delete");
        bookService.deleteCommentById(commentId);
    }
    @ShellMethod(value = "update Comment", key = {"upd_c"})
    public void updateComment(){
        String commentId = ioService.readStringWithPrompt("Please enter comment id for update");
        var title = ioService.readStringWithPrompt("Please enter new text ");
        bookService.updateCommentById(commentId,title);
    }


}
