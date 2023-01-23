package ru.otus.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;
import ru.otus.services.LibraryService;
//import com.github.cloudyrock.mongock.Mongock;
//import com.github.cloudyrock.mongock.SpringMongockBuilder;
//import com.mongodb.client.MongoClient;


@Configuration
public class AppConfig {
	
	

//	static final String CHANGELOGS_PACKAGE = "ru.otus.mongock";
//
//	@Bean
//	public Mongock mongock(MongoProps mongoProps, MongoClient mongoClient) {
//		return new SpringMongockBuilder(mongoClient, mongoProps.getDatabase(), CHANGELOGS_PACKAGE).build();
//	}

	@Bean
	public Book initBookCreation(LibraryService libraryService,BookRepository bookRepository, 
			AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository){
		var nameAuthors = List.of("Alex Pushkin", "Fedor Dostoevsky");
		nameAuthors.forEach(n -> libraryService.addAuthor(n).block());
		var nameGenres = List.of("Horror", "Comdedy");
		nameGenres.forEach(g -> libraryService.addGenre(g).block());
		var levTolstoy = new Author();
		levTolstoy.setName("Lev Tolstoy");
		var levTolstoyAuthor = authorRepository.save(levTolstoy).block();
		var drama = new Genre();
		drama.setName("Drama");
		var dramaGenre = genreRepository.save(drama).block();
		var book = new Book();
		book.setTitle("Peace and War");
		book.setAuthors(List.of(levTolstoyAuthor));
		book.setGenres(List.of(dramaGenre));
		
		var savedBook = bookRepository.save(book).block();
		var comment = new Comment();
		comment.setBook(book);
		comment.setTitle("Test Comment");
		commentRepository.save(comment).block();
		
		return savedBook;
	}
}
