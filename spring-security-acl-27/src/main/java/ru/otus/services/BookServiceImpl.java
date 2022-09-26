package ru.otus.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.exeptions.FindItemExecption;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;
import java.util.Collections;
import java.util.List;


@Component
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final ACLGrantService aclGrantService;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository,
                           GenreRepository genreRepository, CommentRepository commentRepository, ACLGrantService aclGrantService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
        this.commentRepository = commentRepository;
        this.aclGrantService = aclGrantService;
    }


    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    public Page<Book> findPage(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<Book> books = bookRepository.findAll();
        List<Book> list;
        if (books.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, books.size());
            list = books.subList(startItem, toIndex);
        }
        Page<Book> bookPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), books.size());
        return bookPage;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Book addBook(String title, Long authorId, Long genreId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> new FindItemExecption("author not found with id " + authorId));
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> new FindItemExecption("genre not found with id " + genreId));
        Book book = new Book();
        book.setTitle(title);
        book.setAuthors(List.of(author));
        book.setGenres(List.of(genre));
        var savedBook = bookRepository.save(book);
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedBook.getClass(), savedBook.getId());
        aclGrantService.applyACLGranting(objectIdentity);
        return savedBook;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    public Book getBookById(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateBookNameById(Long id, String name) {
        bookRepository.updateBookTitleById(id, name);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Author addAuthor(String authorName) {
        Author author = new Author();
        author.setName(authorName);
        var savedAuthor = authorRepository.save(author);
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedAuthor.getClass(), savedAuthor.getId());
        aclGrantService.applyACLGranting(objectIdentity);
        return savedAuthor;
    }


    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAuthor(Long authorId) {
        authorRepository.deleteById(authorId);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }


    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Genre addGenre(String genreName) {
        Genre genre = new Genre();
        genre.setName(genreName);
        var savedGenre = genreRepository.save(genre);
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedGenre.getClass(), savedGenre.getId());
        aclGrantService.applyACLGranting(objectIdentity);
        return savedGenre;
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteGenre(Long genreId) {
        genreRepository.deleteById(genreId);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    public Comment addComment(Long bookId, String text) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var commment = new Comment();
        commment.setBook(book);
        commment.setTitle(text);
        var savedComment =  commentRepository.save(commment);
        ObjectIdentity objectIdentity = new ObjectIdentityImpl(savedComment.getClass(), savedComment.getId());
        aclGrantService.applyACLGranting(objectIdentity);
        return savedComment;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasAnyRole('ROLE_USER')")
    public List<Comment> findCommentsByBookId(Long bookId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        return commentRepository.findByBook(book);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCommentById(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateCommentById(Long commentId, String text) {
        commentRepository.updateCommentById(commentId, text);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
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
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteAuthorFromBook(Long bookId, Long authorId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var authors = book.getAuthors();
        var author = authorRepository.findById(authorId).orElseThrow(() -> new FindItemExecption("Could not find Author with id" + authorId));
        authors.remove(author);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addGenreForBook(Long bookId, Long genreId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var genres = book.getGenres();
        var genre = genreRepository.findById(genreId).orElseThrow(() -> new FindItemExecption("Could not find Genre with id" + genreId));
        genres.add(genre);
        book.setGenres(genres);
        bookRepository.save(book);
    }

    @Override
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteGenreFromBook(Long bookId, Long genreId) {
        var book = bookRepository.findById(bookId).orElseThrow(() -> new FindItemExecption("book not found with id " + bookId));
        var genres = book.getGenres();
        var genre = genreRepository.findById(genreId).orElseThrow(() -> new FindItemExecption("Could not find Genre with id" + genreId));
        genres.remove(genre);
        book.setGenres(genres);
        bookRepository.save(book);
    }


}
