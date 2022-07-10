package ru.otus.services;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.dao.AuthorDaoJdbc;
import ru.otus.dao.BookDaoJdbc;
import ru.otus.dao.GenreDaoJdbc;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService {

    private final BookDaoJdbc bookDao;
    private final AuthorDaoJdbc authorDao;
    private final GenreDaoJdbc genreDao;
    private final GenreConverter genreConverter;
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;

    public BookServiceImpl(BookDaoJdbc bookDao, AuthorDaoJdbc authorDao, GenreDaoJdbc genreDao,
                           BookConverter bookConverter, GenreConverter genreConverter,
                           AuthorConverter authorConverter) {
        this.bookDao = bookDao;
        this.authorDao = authorDao;
        this.genreDao = genreDao;
        this.genreConverter = genreConverter;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }
    @Transactional
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
    @Transactional
    public String showBookById(long bookId){
        return bookConverter.convert(bookDao.findById(bookId).orElseThrow());
    }
    @Transactional
    public void delBook(long bookId){
        bookDao.delete(bookId);
    }
    @Transactional
    public List<String> getAllAuthors(){
        return authorDao.findAll().stream().map(a -> (authorConverter.convert(a))).collect(Collectors.toList());
    }
    @Transactional
    public long addAuthor(String authorName){
        Author author = new Author();
        author.setName(authorName);
        return authorDao.save(author);
    }
    @Transactional
    public void delAuthor(long authorId){
        authorDao.delete(authorId);
    }
    @Transactional
    public List<String> getAllGenres(){
      return genreDao.findAll().stream().map(genre -> genreConverter.convert(genre)).collect(Collectors.toList());
    }
    @Transactional
    public long addGenre(String genreName){
        Genre genre = new Genre();
        genre.setGenreName(genreName);
        return genreDao.save(genre);
    }
    @Transactional
    public void delGenre(long genreId){
        genreDao.delete(genreId);
    }

}
