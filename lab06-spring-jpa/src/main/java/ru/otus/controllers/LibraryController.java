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

    @ShellMethod(value = "show All Books", key = {"show_books"})
    public void showAllBooks(){
        bookService.getAllBooks().forEach(ioService::outputString);
    }

    @ShellMethod(value = "show By Id", key = {"show_b_id"})
    public void showById(){
        long bookId = ioService.readLongWithPrompt("Please enter book Id");
        ioService.outputString(bookService.getBookById(bookId));
    }

    @ShellMethod(value = "add Book", key = {"add_b"})
    public void addBook(){
        String title = ioService.readStringWithPrompt("Please enter Book name ");
        long authorId = ioService.readLongWithPrompt("Please enter author Id");
        long genreId = ioService.readLongWithPrompt("Please enter genre Id");
        var book = bookService.addBook(title,authorId,genreId);
        ioService.outputString(title + " Is created with ID "+ book.getId());
    }

    @ShellMethod(value = "delete Book", key = {"del_b"})
    public void deleteBook(){
        long bookId = ioService.readLongWithPrompt(" Please enter ID book to Delete ");
        bookService.deleteBook(bookId);
        ioService.outputString("Book with id " + bookId + " is deleted ");
    }

    @ShellMethod(value = "update Book Name By Id", key = {"upd_b_by_id"})
    public void updateNameById(){
        String title = ioService.readStringWithPrompt("Please enter new Book Name");
        long bookId = ioService.readLongWithPrompt("Please enter book id to update");
        bookService.updateBookNameById(bookId,title);
    }
    @ShellMethod(value = "show All Authors", key = {"show_authors"})
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
        var authorId = ioService.readLongWithPrompt("Please enter Author Id");
        bookService.deleteAuthor(authorId);
        ioService.outputString("Author was deleted ");
    }

    @ShellMethod(value = "show All Genres", key = {"show_genres"})
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
        var genreId = ioService.readLongWithPrompt("Please provide Genre ID");
        bookService.deleteGenre(genreId);
        ioService.outputString("Genre with ID " + genreId + " is deleted ");
    }
    @ShellMethod(value = "show Comments", key = {"show_c"})
    public void showCommentsOfBook(){
        var bookId = ioService.readLongWithPrompt("Please enter book Id");
        var result = bookService.findCommentsByBookId(bookId);
        result.forEach(c -> ioService.outputString("id "+ c.getId() + " | " + c.getTitle()));
    }
    @ShellMethod(value = "add Comment", key = {"add_c"})
    public void addCommentForBook(){
        var bookId = ioService.readLongWithPrompt("Please Enter book id for comment");
        var title = ioService.readStringWithPrompt("Please enter text of your comment");
        var comment = bookService.addComment(bookId,title);
        ioService.outputString("Your comment was added with id" + comment.getId());
    }
    @ShellMethod(value = "delete Comment", key = {"del_c"})
    public void deleteComment(){
        var commentId = ioService.readLongWithPrompt("Please enter id of comment to delete");
        bookService.deleteCommentById(commentId);
    }
    @ShellMethod(value = "update Comment", key = {"upd_c"})
    public void updateComment(){
        var commentId = ioService.readLongWithPrompt("Please enter comment id for update");
        var title = ioService.readStringWithPrompt("Please enter new text ");
        bookService.updateCommentById(commentId,title);
    }


}
