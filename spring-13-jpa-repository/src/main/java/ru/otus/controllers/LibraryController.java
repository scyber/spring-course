package ru.otus.controllers;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.domain.Genre;
import ru.otus.services.BookService;
import ru.otus.services.IOService;

@ShellComponent
public class LibraryController {

    private final BookService bookService;
    private final IOService ioService;

    public LibraryController(BookService bookService, IOService ioService) {
        this.bookService = bookService;
        this.ioService = ioService;
    }

    @ShellMethod(value = "showAllBooks", key = {"show_books"})
    public void showAllBooks(){
        bookService.getAllBooks().forEach(s -> ioService.outputString(s));
    }

    @ShellMethod(value = "showById", key = {"show_b_id"})
    public void showById(){
        long bookId = ioService.readLongWithPrompt("Please enter book Id");
        ioService.outputString(bookService.getBookById(bookId));
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
        long bookId = ioService.readLongWithPrompt(" Please enter ID book to Delete ");
        bookService.deleteBook(bookId);
        ioService.outputString("Book with id " + bookId + " is deleted ");
    }

    @ShellMethod(value = "updateBookNameById", key = {"upd_b_by_id"})
    public void updateNameById(){
        String title = ioService.readStringWithPrompt("Please enter new Book Name");
        long bookId = ioService.readLongWithPrompt("Please enter book id to update");
        bookService.updateBookNameById(bookId,title);
    }
    @ShellMethod(value = "showAllAuthors", key = {"show_authors"})
    public void showAllAuthors(){
        bookService.getAllAuthors().forEach(a -> ioService.outputString(a));
    }

    @ShellMethod(value = "add_author", key = {"add_a"})
    public void addAuthor(){
        String authorName = ioService.readStringWithPrompt("Please enter Author Name");
        var author = bookService.addAuthor(authorName);
        ioService.outputString("Author with added with id " + author);
    }

    @ShellMethod(value = "deleteAuthor", key = {"del_a"})
    public void deleteAuthor(){
        Long authorId = ioService.readLongWithPrompt("Please enter Author Id");
        bookService.deleteAuthor(authorId);
        ioService.outputString("Author was deleted ");
    }

    @ShellMethod(value = "showAllGenres", key = {"show_genres"})
    public void showAllGenres(){
        bookService.getAllGenres().forEach(g -> ioService.outputString(g));
    }

    @ShellMethod(value = "add_genre", key = {"add_g"})
    public void addGenre(){
        String genreName = ioService.readStringWithPrompt("Please enter Genre name");
        Genre genre = bookService.addGenre(genreName);
        ioService.outputString("Genre with was added with id " + genre.getId());
    }

    @ShellMethod(value = "delGenre", key = {"del_g"})
    public void deleteGenre(){
        Long genreId = ioService.readLongWithPrompt("Please provide Genre ID");
        bookService.deleteGenre(genreId);
        ioService.outputString("Genre with ID " + genreId + " is deleted ");
    }

}
