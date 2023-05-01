package ru.otus.services;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.clients.LibraryClient;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.exeptions.FindItemExecption;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;
import java.util.List;
import java.util.stream.Collectors;


@Component
@RequiredArgsConstructor
public class LibraryServiceImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;


    @Override
    @Transactional(readOnly = true)
    @BatchSize(size = 10)
    public Page<Book> findPage(Pageable pageable) {
        return bookRepository.findAll(
                pageable);
    }

//    @Override
//    @BatchSize(size = 10)
//    @Transactional(readOnly = true)
//    public List<Book> getAllBooks() {
//        return bookRepository.findAll();
//    }

    @Override
    @Transactional
    public Book addBook(Book book) {

        var authorsIdList = book.getAuthors().stream().map(a ->a.getId()).collect(Collectors.toList());
        var authors = authorRepository.findAllById(authorsIdList);
        var genresIdList = book.getGenres().stream().map(a->a.getId()).collect(Collectors.toList());
        var genres = genreRepository.findAllById(genresIdList);
        book.setGenres(genres);
        book.setAuthors(authors);
        return bookRepository.save(book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
    }

    @Override
    @Transactional
    public void deleteBook(Long bookId) {
            bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    public void updateBookNameById(Long id, String name) {
        bookRepository.updateBookTitleById(id, name);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
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
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
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
    public void deleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    @Transactional
    public Comment addComment(Book book, String text) {

        var bookSaved  = bookRepository.findById(book.getId()).orElseThrow(() -> new FindItemExecption("book not found with id " + book));
        var commment = new Comment();
        commment.setBook(bookSaved);
        commment.setTitle(text);
        return commentRepository.save(commment);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(Long bookId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        return commentRepository.findByBook(book);
    }

    @Override
    @Transactional
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    public void updateCommentById(Long commentId, String text) {
        commentRepository.updateCommentById(commentId, text);
    }

    @Override
    @Transactional
    public void addAuthorForBook(Long bookId, Long authorId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var authors = book.getAuthors();
        var author = authorRepository.findById(authorId).orElseThrow(() -> new FindItemExecption("Could not find Author with id" + authorId));
        authors.add(author);
        book.setAuthors(authors);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void deleteAuthorFromBook(Long bookId, Long authorId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var authors = book.getAuthors();
        var author = authorRepository.findById(authorId).orElseThrow(() -> new FindItemExecption("Could not find Author with id" + authorId));
        authors.remove(author);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void addGenreForBook(Long bookId, Long genreId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var genres = book.getGenres();
        var genre = genreRepository.findById(genreId).orElseThrow(() -> new FindItemExecption("Could not find Genre with id" + genreId));
        genres.add(genre);
        book.setGenres(genres);
        bookRepository.save(book);
    }

    @Override
    public void deleteGenreFromBook(Long bookId, Long genreId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var genres = book.getGenres();
        var genre = genreRepository.findById(genreId).orElseThrow(() -> new FindItemExecption("Could not find Genre with id" + genreId));
        genres.remove(genre);
        book.setGenres(genres);
        bookRepository.save(book);
    }



}
