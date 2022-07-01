package ru.otus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.Locale;

@ShellComponent
public class BookShellService {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private AuthorDao authorDao;
    @Autowired
    private GenreDao genreDao;
    @Autowired
    private GenreConverter genreConverter;
    @Autowired
    private BookConverter bookConverter;
    @Autowired
    private AuthorConverter authorConverter;
    @Autowired
    private IOService ioService;
    @Autowired
    private MessageSource messageSource;

    @ShellMethod(value = "finish", key = {"f"})
    public void finish(){
        System.exit(0);
    }

    @ShellMethod(value = "showAllBooks", key = {"show_books"})
    public void showAllBooks(){
        bookDao.findAll().forEach(book -> {
            System.out.println(this.bookConverter.convert(book));
        });
    }
    @ShellMethod(value = "showAllAuthors", key = {"show_authors"})
    public void showAllAuthors(){
        authorDao.findAll().forEach(a -> ioService.outputString(authorConverter.convert(a)));
    }
    @ShellMethod(value = "showAllGenres", key = {"show_genres"})
    public void showAllGenres(){
        Iterable<Genre> genres = genreDao.findAll();
        genres.forEach(g-> ioService.outputString(genreConverter.convert(g)));
    }
    @ShellMethod(value = "add_book", key = {"add_b"})
    public void addBook(){
        String promptName = messageSource.getMessage("book.name",null, Locale.getDefault());
        String bookName = ioService.readStringWithPrompt(promptName);
        String authorIdPrompt = messageSource.getMessage("author.id",null,Locale.getDefault());
        Long authorId = ioService.readLongWithPrompt(authorIdPrompt);
        String genreIdPrompt = messageSource.getMessage("genre.id", null, Locale.getDefault());
        Long genreId = ioService.readLongWithPrompt(genreIdPrompt);
        Book book = new Book();
        book.setName(bookName);
        book.setAuthorId(authorId);
        book.setGenreId(genreId);
        Long bookId = bookDao.save(book);
        String doneMessage = messageSource.getMessage("book.done", null, Locale.getDefault());
        ioService.outputString(bookName + " " + doneMessage + " "+ bookId);
    }
    @ShellMethod(value = "add_genre", key = {"add_g"})
    public void addGenre(){
        Genre genre = new Genre();
        String gernePrompt = messageSource.getMessage("genre.name", null,Locale.getDefault());
        String genreName = ioService.readStringWithPrompt(gernePrompt);
        genre.setGenreName(genreName);
        genreDao.save(genre);
        String messageDoneWithId = messageSource.getMessage("genre.done.id", null, Locale.getDefault());
        ioService.outputString(messageDoneWithId);
    }
    @ShellMethod(value = "add_author", key = {"add_a"})
    public void addAuthor(){
        Author author = new Author();
        String firstNamePrompt = messageSource.getMessage("author.first.name", null, Locale.getDefault());
        String firstName = ioService.readStringWithPrompt(firstNamePrompt);
        String secNamePrompt = messageSource.getMessage("author.sec.name", null, Locale.getDefault());
        String secondName = ioService.readStringWithPrompt(secNamePrompt);
        String lastNamePrompt = messageSource.getMessage("author.last.name", null,Locale.getDefault());
        String lastName = ioService.readStringWithPrompt(lastNamePrompt);
        author.setFirstName(firstName);
        author.setSecondName(secondName);
        author.setLastName(lastName);
        authorDao.save(author);
        String messageDoneAuthor = messageSource.getMessage("author.done.id", null, Locale.getDefault());
        ioService.outputString(messageDoneAuthor);
    }
    @ShellMethod(value = "deleteBook", key = {"del_b"})
    public void deleteBook(){
        String bookIdPrompt = messageSource.getMessage("book.id", null, Locale.getDefault());
        Long bookId = ioService.readLongWithPrompt(bookIdPrompt);
        bookDao.delete(bookId);
    }
    @ShellMethod(value = "deleteGenre", key = {"del_g"})
    public void deleteGenre(){
        String genreIdPrompt = messageSource.getMessage("genre.id", null,Locale.getDefault());
        Long genreId = ioService.readLongWithPrompt(genreIdPrompt);
        genreDao.delete(genreId);
    }
    @ShellMethod(value = "deleteAuthor", key = {"del_a"})
    public void deleteAuthor(){
        String authorIdPrompt = messageSource.getMessage("author.id", null, Locale.getDefault());
        Long authorId = ioService.readLongWithPrompt(authorIdPrompt);
        authorDao.delete(authorId);
    }

}
