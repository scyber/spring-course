package ru.otus.services;

import java.util.ArrayList;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;


@Component
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;


    public LibraryServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                              GenreRepository genreRepository, CommentRepository commentRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;

    }


    @Override
    public Mono<Page<Book>> findPage(PageRequest request) {
        return bookRepository.findAllBy(request)
        		.collectList()
        		.zipWith(this.bookRepository.count())
        		.map(t -> new PageImpl<>(t.getT1(),request,t.getT2()));
    }

    @Override
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Mono<Book> addBook(Book book) {

        var authorsIdList = book.getAuthors().stream().map(a ->a.getId()).collect(Collectors.toList());
       
        var authorsRecivedList = new ArrayList<Author>();
        authorRepository.findAllById(authorsIdList).collectList().subscribe(authorsRecivedList::addAll);
        var genresIdList = book.getGenres().stream().map(a->a.getId()).collect(Collectors.toList());
        var genreRecievedList = new ArrayList<Genre>();
        genreRepository.findAllById(genresIdList).collectList().subscribe(genreRecievedList::addAll);
        
        book.setGenres(genreRecievedList);
        book.setAuthors(authorsRecivedList);
        return bookRepository.save(book);
    }

    @Override
    public Mono<Book> getBookById(String bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Mono<Void> deleteBook(String bookId) {
           return bookRepository.deleteById(bookId);
    }

    @Override
    public Mono<Void> updateBookNameById(String id, String name) {
        return bookRepository.updateBookTitleById(id, name);
    }

    @Override
    public Flux<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Mono<Author> addAuthor(String authorName) {
        Author author = new Author();
        author.setName(authorName);
        return authorRepository.save(author);
    }


    @Override
    public Mono<Void> deleteAuthor(String authorId) {
        return authorRepository.deleteById(authorId);
    }

    @Override
    public Flux<Genre> getAllGenres() {
        return genreRepository.findAll();
    }


    @Override
    public Mono<Genre> addGenre(String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);
        return genreRepository.save(genre);
    }

    @Override
    @Transactional
    public Mono<Void> deleteGenre(String genreId) {
       return genreRepository.deleteById(genreId);
    }

    @Override
    public Flux<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public Mono<Comment> addComment(Book book, String text) {
    	
        var bookSaved  = bookRepository.findById(book.getId()).block();
        var commment = new Comment();
        commment.setBook(bookSaved);
        commment.setTitle(text);
        return commentRepository.save(commment);
    }

    @Override
    public Flux<Comment> findCommentsByBookId(String bookId) {
        var book = bookRepository.findById(bookId).block();
        return commentRepository.findByBook(book);
    }

    @Override
    public Mono<Void> deleteCommentById(String commentId) {
      return commentRepository.deleteById(commentId);
    }

    @Override
    public Mono<Void> updateCommentById(String commentId, String text) {
       return commentRepository.updateCommentById(commentId, text);
    }

    @Override
    @Transactional
    public Mono<Book> addAuthorForBook(String bookId, String authorId) {
        var book = bookRepository.findById(bookId).block();
        var authors = book.getAuthors();
        var author = authorRepository.findById(authorId).block();
        authors.add(author);
        book.setAuthors(authors);
       return bookRepository.save(book);
    }

    @Override
    public Mono<Book> deleteAuthorFromBook(String bookId, String authorId) {
        var book = bookRepository.findById(bookId).block();
        var authors = book.getAuthors();
        var author = authorRepository.findById(authorId).block();
        authors.remove(author);
        return bookRepository.save(book);
    }

    @Override
    public Mono<Book> addGenreForBook(String bookId, String genreId) {
        var book = bookRepository.findById(bookId).block();
        var genres = book.getGenres();
        var genre = genreRepository.findById(genreId).block();
        genres.add(genre);
        book.setGenres(genres);
        return bookRepository.save(book);
    }

    @Override
    public Mono<Book> deleteGenreFromBook(String bookId, String genreId) {
        var book = bookRepository.findById(bookId).block();
        var genres = book.getGenres();
        var genre = genreRepository.findById(genreId).block();
        genres.remove(genre);
        book.setGenres(genres);
        return bookRepository.save(book);
    }



}
