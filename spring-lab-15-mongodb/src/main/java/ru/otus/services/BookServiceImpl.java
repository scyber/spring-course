package ru.otus.services;

import org.springframework.stereotype.Component;

import org.springframework.transaction.annotation.Transactional;
import ru.otus.converters.AuthorConverter;
import ru.otus.converters.BookConverter;
import ru.otus.converters.GenreConverter;
import ru.otus.model.Author;
import ru.otus.model.Book;
import ru.otus.model.Comment;
import ru.otus.model.Genre;
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
    public Book addBook(String title, String authorId, String genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow();
        Genre genre = genreRepository.findById(genreId).orElseThrow();
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public String getBookById(String bookId) {
        return bookConverter.convert(bookRepository.findById(bookId).orElseThrow());
    }

    @Override
    @Transactional
    public void deleteBook(String bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public void updateBookNameById(String id, String name) {
        bookRepository.updateBookTitleById(id, name);
    }

    @Override
    @Transactional(readOnly = true)
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
    public void deleteAuthor(String authorId) {
        authorRepository.deleteById(authorId);
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
    public void deleteGenre(String genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    @Transactional
    public Comment addComment(String bookId, String text) {
        var book = bookRepository.findById(bookId).get();
        var commment = new Comment();
        commment.setBook(book);
        commment.setTitle(text);
        return commentRepository.save(commment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(String bookId) {
        return commentRepository.findByBookId(bookId);
    }

    @Override
    @Transactional
    public void deleteCommentById(String commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public void updateCommentById(String commentId, String text) {
        commentRepository.updateCommentById(commentId, text);
    }

}
