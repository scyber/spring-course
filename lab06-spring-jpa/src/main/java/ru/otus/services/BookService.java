package ru.otus.services;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepositoryJpa;
import ru.otus.repository.BookRepositoryJpa;
import ru.otus.repository.GenreRepositoryJpa;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookService {


    private final BookRepositoryJpa bookRepository;
    private final AuthorRepositoryJpa authorRepository;
    private final GenreRepositoryJpa genreRepositoryJpa;

    private final GenreConverter genreConverter;
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;

    public BookService(BookRepositoryJpa bookRepository, AuthorRepositoryJpa authorRepository, GenreRepositoryJpa genreRepositoryJpa,
                       BookConverter bookConverter, GenreConverter genreConverter,
                       AuthorConverter authorConverter) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepositoryJpa = genreRepositoryJpa;
        this.genreConverter = genreConverter;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }
    @Transactional
    public List<String> getAllBooks(){
      return  bookRepository.findAll().stream().map(book -> this.bookConverter.convert(book)).collect(Collectors.toList());
    }
    @Transactional
    public long addBook(String title, long authorId, long genreId ){
        Author author = authorRepository.findById(authorId).get();
        Genre genre = genreRepositoryJpa.findById(genreId).get();
        Book book = new Book();
        book.setName(title);
        book.setAuthor(author);
        book.setGenre(genre);
        Long bookId = bookRepository.save(book).getId();
        return bookId;
    }
    @Transactional
    public String showById(long bookId){
        return bookConverter.convert(bookRepository.findById(bookId).get());
    }
    @Transactional
    public void delBook(long bookId){
        Book book = bookRepository.findById(bookId).get();
        bookRepository.delete(bookId);
    }
    @Transactional
    public List<String> getAllAuthors(){
        return authorRepository.findAll().stream().map(a -> (authorConverter.convert(a))).collect(Collectors.toList());
    }
    @Transactional
    public Author addAuthor(String authorName){
        Author author = new Author();
        author.setName(authorName);
        return authorRepository.save(author);
    }
    @Transactional
    public void delAuthor(long authorId){
        authorRepository.deleteById(authorId);
    }
    @Transactional
    public List<String> getAllGenres(){
      return genreRepositoryJpa.findAll().stream().map(genre -> genreConverter.convert(genre)).collect(Collectors.toList());
    }
    @Transactional
    public Genre addGenre(String genreName){
        Genre genre = new Genre();
        genre.setName(genreName);
        return genreRepositoryJpa.save(genre);
    }
    @Transactional
    public void delGenre(long genreId){
        genreRepositoryJpa.deleteById(genreId);
    }

}
