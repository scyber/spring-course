package ru.otus.services;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class BookShellController {

    private BookShellProcessor bookShellProcessor;

    public BookShellController(BookShellProcessor bookShellProcessor) {
        this.bookShellProcessor = bookShellProcessor;
    }

    @ShellMethod(value = "showAllBooks", key = {"show_books"})
    public void showAllBooks(){
        bookShellProcessor.showAllBookProcess();
    }
    @ShellMethod(value = "showById", key = {"show_b_id"})
    public void showById(){
        bookShellProcessor.showByIdProcess();
    }
    @ShellMethod(value = "addBook", key = {"add_b"})
    public void addBook(){
        bookShellProcessor.addBookProcess();
    }
    @ShellMethod(value = "deleteBook", key = {"del_b"})
    public void deleteBook(){
        bookShellProcessor.delBookProcess();
    }

    @ShellMethod(value = "showAllAuthors", key = {"show_authors"})
    public void showAllAuthors(){
        bookShellProcessor.showAuthorsProcess();
    }
    @ShellMethod(value = "add_author", key = {"add_a"})
    public void addAuthor(){
        bookShellProcessor.addAuthorProcess();
    }
    @ShellMethod(value = "deleteAuthor", key = {"del_a"})
    public void deleteAuthor(){
        bookShellProcessor.delAuthorProcess();
    }
    @ShellMethod(value = "showAllGenres", key = {"show_genres"})
    public void showAllGenres(){
        bookShellProcessor.showAllGenresProcess();
    }
    @ShellMethod(value = "add_genre", key = {"add_g"})
    public void addGenre(){
        bookShellProcessor.addGenreProcess();
    }
    @ShellMethod(value = "delGenre", key = {"del_g"})
    public void deleteGenre(){
        bookShellProcessor.delGenreProcess();
    }

}
