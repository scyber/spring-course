package ru.otus.services;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

    private final CommentRepository commentRepository;
    private final GenreConverter genreConverter;
    private final BookConverter bookConverter;
    private final AuthorConverter authorConverter;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, CommentRepository commentRepository,
                           BookConverter bookConverter, GenreConverter genreConverter, AuthorConverter authorConverter) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.genreConverter = genreConverter;
        this.bookConverter = bookConverter;
        this.authorConverter = authorConverter;
    }


    @Override
    @Transactional(readOnly = true)
    public List<String> getAllBooks() {
        return bookRepository.findAll().stream().map(this.bookConverter::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Book addBook(String title, long authorId, long genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        Genre genre = genreRepository.findById(genreId).orElseThrow();
        Book book = new Book();
        book.setTitle(title);
        book.setAuthor(author);
        book.setGenre(genre);
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public String getBookById(long bookId) {
        return bookConverter.convert(bookRepository.findById(bookId).orElseThrow());
    }

    @Override
    @Transactional
    public void deleteBook(long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public void updateBookNameById(long id, String name) {
        bookRepository.updateBookTitleById(id, name);
    }

    @Override
    @Transactional
    public List<String> getAllAuthors() {
        return authorRepository.findAll().stream().map(authorConverter::convert).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Author addAuthor(String authorName) {
        Author author = new Author();
        author.setName(authorName);
        return authorRepository.save(author);
    }


    @Override
    @Transactional
    public void deleteAuthor(long authorId) {
        authorRepository.delete(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllGenres() {
        return genreRepository.findAll().stream().map(genreConverter::convert).collect(Collectors.toList());
    }


    @Override
    @Transactional
    public Genre addGenre(String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public void deleteGenre(long genreId) {
        genreRepository.delete(genreId);
    }

    @Override
    @Transactional
    public Comment addComment(long bookId, String text) {
        return commentRepository.addCommentBookById(bookId, text);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(long bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteCommentById(long commentId) {
        commentRepository.delete(commentId);
    }

    @Override
    @Transactional
    public void updateCommentById(long commentId, String text) {
        commentRepository.updateCommentById(commentId, text);
    }

}
