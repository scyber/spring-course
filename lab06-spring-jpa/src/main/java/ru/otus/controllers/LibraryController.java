package ru.otus.controllers;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Author;
import ru.otus.services.BookService;
import ru.otus.services.IOService;

@ShellComponent
public class LibraryController {

    private final BookService bookService;
    private final IOService ioService;

    public LibraryController(BookService bookShellProcessor, IOService ioService) {
        this.bookService = bookShellProcessor;
        this.ioService = ioService;
    }

    @ShellMethod(value = "showAllBooks", key = {"show_books"})
    public void showAllBooks(){
        bookService.getAllBooks().forEach(s -> ioService.outputString(s));
    }
    @ShellMethod(value = "showById", key = {"show_b_id"})
    public void showById(){
        long bookId = ioService.readLongWithPrompt("Please enter book Id");
        ioService.outputString(bookService.showById(bookId));
    }

    @ShellMethod(value = "addBook", key = {"add_b"})
    public void addBook(){
        String title = ioService.readStringWithPrompt("Please enter Book name ");
        long authorId = ioService.readLongWithPrompt("Please enter author Id");
        long genreId = ioService.readLongWithPrompt("Please enter genre Id");
        long bookId = bookService.addBook(title,authorId,genreId);
        ioService.outputString(title + " Is created with ID "+ bookId);
    }

    @ShellMethod(value = "deleteBook", key = {"del_b"})
    public void deleteBook(){
        Long bookId = ioService.readLongWithPrompt(" Please enter ID book to Delete ");
        bookService.delBook(bookId);
        ioService.outputString("Book with id " + bookId + " is deleted ");
    }

    @ShellMethod(value = "showAllAuthors", key = {"show_authors"})
    public void showAllAuthors(){
        bookService.getAllAuthors().forEach(a -> ioService.outputString(a));
    }

    @ShellMethod(value = "add_author", key = {"add_a"})
    public void addAuthor(){
        String authorName = ioService.readStringWithPrompt("Please enter Author Name");
        Author author = bookService.addAuthor(authorName);
        ioService.outputString("Author with added with id " + author.getId());
    }

    @ShellMethod(value = "deleteAuthor", key = {"del_a"})
    public void deleteAuthor(){
        Long authorId = ioService.readLongWithPrompt("Please enter Author Id");
        bookService.delAuthor(authorId);
        ioService.outputString("Author was deleted ");
    }

    @ShellMethod(value = "showAllGenres", key = {"show_genres"})
    public void showAllGenres(){
        bookService.getAllGenres().forEach(g -> ioService.outputString(g));
    }

    @ShellMethod(value = "add_genre", key = {"add_g"})
    public void addGenre(){
        String genreName = ioService.readStringWithPrompt("Please enter Genre name");
        long genreId = bookService.addGenre(genreName).getId();
        ioService.outputString("Genre with was added with id " + genreId);
    }

    @ShellMethod(value = "delGenre", key = {"del_g"})
    public void deleteGenre(){
        Long genreId = ioService.readLongWithPrompt("Please provide Genre ID");
        bookService.delGenre(genreId);
        ioService.outputString("Genre with ID " + genreId + " is deleted ");
    }

}
