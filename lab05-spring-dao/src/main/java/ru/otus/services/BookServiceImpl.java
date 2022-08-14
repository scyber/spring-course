package ru.otus.services;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.dao.*;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final GenreConverter genreConverter;
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;

    public BookServiceImpl(BookDao bookDao, AuthorDao authorDao, GenreDao genreDao,
                           BookConverter bookConverter, GenreConverter genreConverter,
                           AuthorConverter authorConverter) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.genreConverter = genreConverter;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllBooks(){
      return  bookDao.findAll().stream().map(book -> this.bookConverter.convert(book)).collect(Collectors.toList());
    }

    @Transactional
    public long addBook(String title, long authorId, long genreId ){
        Author author = authorDao.findById(authorId).orElseThrow();
        Genre genre = genreDao.findById(genreId).orElseThrow();
        Book book = new Book();
        book.setName(title);
        book.setAuthor(author);
        book.setGenre(genre);
        Long bookId = bookDao.save(book);
        return bookId;
    }
    @Transactional(readOnly = true)
    @Override
    public String getBookById(long bookId){
        return bookConverter.convert(bookDao.findById(bookId).orElseThrow());
    }

    @Transactional
    @Override
    public void deleteBook(long bookId){
        bookDao.delete(bookId);
    }

    @Transactional
    @Override
    public void updateBookNameById(long id, String name) {
        bookDao.updateNameById(id,name);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllAuthors(){
        return authorDao.findAll().stream().map(a -> (authorConverter.convert(a))).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public long addAuthor(String authorName){
        Author author = new Author();
        author.setName(authorName);
        return authorDao.save(author);
    }
    @Transactional
    @Override
    public void deleteAuthor(long authorId){
        authorDao.delete(authorId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllGenres(){
      return genreDao.findAll().stream().map(genre -> genreConverter.convert(genre)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public long addGenre(String genreName){
        Genre genre = new Genre();
        genre.setName(genreName);
        return genreDao.save(genre);
    }

    @Transactional
    @Override
    public void deleteGenre(long genreId){
        genreDao.delete(genreId);
    }

}
