package ru.otus.services;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.GenreRepository;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final GenreConverter genreConverter;
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, BookConverter bookConverter,
                            GenreConverter genreConverter, AuthorConverter authorConverter) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.genreConverter = genreConverter;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllBooks(){
      return  bookRepository.findAll().stream().map(book -> this.bookConverter.convert(book)).collect(Collectors.toList());
    }

    @Transactional
    public long addBook(String title, long authorId, long genreId ){
        Author author = authorRepository.findById(authorId).orElseThrow();
        Genre genre = genreRepository.findById(genreId).orElseThrow();
        Book book = new Book();
        book.setName(title);
        book.setAuthor(author);
        book.setGenre(genre);
        Long bookId = bookRepository.save(book).getId();
        return bookId;
    }
    @Transactional(readOnly = true)
    @Override
    public String getBookById(long bookId){
        return bookConverter.convert(bookRepository.findById(bookId).orElseThrow());
    }

    @Transactional
    @Override
    public void deleteBook(long bookId){
        bookRepository.deleteById(bookId);
    }

    @Transactional
    @Override
    public void updateBookNameById(long id, String name) {
        bookRepository.updateBookNameById(id,name);
    }

    @Transactional
    @Override
    public List<String> getAllAuthors(){
        return authorRepository.findAll().stream().map(a -> (authorConverter.convert(a))).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Author addAuthor(String authorName){
        Author author = new Author();
        author.setName(authorName);
        return authorRepository.save(author);
    }
    @Transactional
    @Override
    public void deleteAuthor(long authorId){
        authorRepository.delete(authorId);
    }

    @Transactional(readOnly = true)
    @Override
    public List<String> getAllGenres(){
      return genreRepository.findAll().stream().map(genre -> genreConverter.convert(genre)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Genre addGenre(String genreName){
        Genre genre = new Genre();
        genre.setName(genreName);
        return genreRepository.save(genre);
    }

    @Transactional
    @Override
    public void deleteGenre(long genreId){
        genreRepository.delete(genreId);
    }

}
