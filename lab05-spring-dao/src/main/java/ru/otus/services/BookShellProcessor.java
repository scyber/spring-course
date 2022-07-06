package ru.otus.services;

import org.springframework.stereotype.Component;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.BookDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

@Component
public class BookShellProcessor {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final GenreConverter genreConverter;
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;
    private final IOService ioService;

    public BookShellProcessor(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao,
                              BookConverter bookConverter,GenreConverter genreConverter,
                              AuthorConverter authorConverter, IOService ioService) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.genreConverter = genreConverter;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
        this.ioService = ioService;
    }

    public void showAllBookProcess(){
        bookDao.findAll().forEach(book -> {
            ioService.outputString(this.bookConverter.convert(book));
        });
    }
    public void addBookProcess(){
        String bookName = ioService.readStringWithPrompt("Please enter Book name ");
        Long authorId = ioService.readLongWithPrompt("Please enter author ID ");
        Long genreId = ioService.readLongWithPrompt("Please enter genre ID ");
        Book book = new Book();
        book.setName(bookName);
        Author author = new Author();
        author.setId(authorId);
        book.setAuthor(author);
        Genre genre = new Genre();
        genre.setId(genreId);
        book.setGenre(genre);
        Long bookId = bookDao.save(book);
        ioService.outputString(bookName + " Is created with ID "+ bookId);
    }
    public void showByIdProcess(){
        Long id = ioService.readLongWithPrompt(" Enter book Id ");
        ioService.outputString(bookConverter.convert(bookDao.findById(id)));
    }
    public void delBookProcess(){
        Long bookId = ioService.readLongWithPrompt(" Please enter ID book to Delete ");
        bookDao.delete(bookId);
        ioService.outputString("Book with id " + bookId + " is deleted ");
    }
    public void showAuthorsProcess(){
        authorDao.findAll().forEach(a -> ioService.outputString(authorConverter.convert(a)));
    }
    public void addAuthorProcess(){
        Author author = new Author();
        String name = ioService.readStringWithPrompt(" Please enter Author Name ");
        author.setName(name);
        long id = authorDao.save(author);
        ioService.outputString("Author with added with id " + id);
    }
    public void delAuthorProcess(){
        Long authorId = ioService.readLongWithPrompt("Please enter Author Name");
        authorDao.delete(authorId);
    }
    public void showAllGenresProcess(){
        genreDao.findAll().forEach(genre -> ioService.outputString(genreConverter.convert(genre)));
    }
    public void addGenreProcess(){
        Genre genre = new Genre();
        String genreName = ioService.readStringWithPrompt("Please enter Genre name");
        genre.setGenreName(genreName);
        long idGenre = genreDao.save(genre);
        ioService.outputString(genreName + " was added with ID " + idGenre);
    }
    public void delGenreProcess(){
        Long genreId = ioService.readLongWithPrompt("Please provide Genre ID");
        genreDao.delete(genreId);
        ioService.outputString("Genre with ID " + genreId + " is deleted ");
    }



}
