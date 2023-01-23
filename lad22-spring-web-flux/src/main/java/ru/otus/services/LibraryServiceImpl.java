package ru.otus.services;

import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import lombok.extern.log4j.Log4j2;
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
@Log4j2
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
				.collectList().zipWith(this.bookRepository.count())
				.map(t -> new PageImpl<>(t.getT1(), request, t.getT2()));
	}

	@Override
	public Flux<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	public Mono<Book> addBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Mono<Book> getBookById(String bookId) {
		return bookRepository.findById(bookId);
	}

	@Override
	public Mono<Book> deleteBook(Book book) {
		return bookRepository.delete(book).thenReturn(book);
	}

	@Override
	public Mono<Book> updateBookNameById(String id, String title) {
		return bookRepository.findById(id).mapNotNull(b -> {
			b.setTitle(title);
			return b;
		}).flatMap(bookRepository::save);
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
	public Mono<Comment> addComment(String bookId, String title) {
		return this.bookRepository.findById(bookId).mapNotNull(b -> {
			log.debug("book for add comment found ID " + b.getId());
			var comment = new Comment();
			comment.setTitle(title);
			comment.setBook(b);
			return comment;
		}).flatMap(this.commentRepository::save);

	}

	@Override
	public Flux<Comment> findCommentsByBookId(String bookId) {
		return this.commentRepository.findAllByBookId(bookId);
	}

	@Override
	public Mono<Void> deleteCommentById(String commentId) {
		return commentRepository.deleteById(commentId);
	}

	@Override
	public Mono<Comment> updateCommentById(String commentId, String text) {
		return commentRepository.findById(commentId).map(comment -> {
			comment.setTitle(text);
			return comment;
		}).flatMap(commentRepository::save);
	}

	@Override
	public Mono<Book> addAuthorForBook(String bookId, String authorId) {
	  return this.bookRepository.findById(bookId).map(b -> {
			var authorsForUpdate = b.getAuthors();
			var authorNew = this.authorRepository.findById(authorId).block();
			authorsForUpdate.add(authorNew);
			b.setAuthors(authorsForUpdate);
			return b;
		}).flatMap(this.bookRepository::save);
	}

	@Override
	public Mono<Book> deleteAuthorFromBook(String bookId, String authorId) {
		return this.bookRepository.findById(bookId).map(b -> {
			var authorsForUpdate = b.getAuthors().stream().filter(a -> a.getId() != authorId).collect(Collectors.toList());
			b.setAuthors(authorsForUpdate);
			return b;
		}).flatMap(this.bookRepository::save);
	}

	@Override
	public Mono<Book> addGenreForBook(String bookId, String genreId) {
		return bookRepository.findById(bookId).map(b -> {
			var genres = b.getGenres();
			genreRepository.findById(genreId).doOnEach(g -> genres.add(g.get()));
			b.setGenres(genres);
			return b;
		}).flatMap(bookRepository::save);

	}

	@Override
	public Mono<Book> deleteGenreFromBook(String bookId, String genreId) {

		return bookRepository.findById(bookId).map(book -> {
			var genres = book.getGenres().stream().filter(g -> g.getId() != genreId).collect(Collectors.toList());
			book.setGenres(genres);
			return book;
		}).flatMap(bookRepository::save);

	}

	@Override
	public Mono<Void> deleteBookById(String id) {
		return bookRepository.deleteById(id);
	}

}
