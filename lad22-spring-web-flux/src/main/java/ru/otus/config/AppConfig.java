package ru.otus.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import com.github.cloudyrock.mongock.Mongock;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.connection.ClusterSettings;
import com.mongodb.connection.ConnectionPoolSettings;
import com.mongodb.connection.ServerSettings;
import com.mongodb.connection.SocketSettings;
import com.mongodb.connection.SslSettings;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Comment;
import ru.otus.domain.Genre;
import ru.otus.repository.AuthorRepository;
import ru.otus.repository.BookRepository;
import ru.otus.repository.CommentRepository;
import ru.otus.repository.GenreRepository;
import ru.otus.services.LibraryService;
import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
//import com.mongodb.client.MongoClient;


@Configuration
@Log4j2
public class AppConfig {


	static final String CHANGELOGS_PACKAGE = "ru.otus.mongock";
	
	
//	@Bean
//	public Mongock mongock(MongoProps mongoProps, MongoClient mongoClient) {
//        return new SpringMongockBuilder(mongoClient, mongoProps.getDatabase(), CHANGELOGS_PACKAGE)
//                .build();
//    }
	
	

    @Bean
    Book initBookCreation(LibraryService libraryService, BookRepository bookRepository,
                                                                                                                                                                                                                                                                                                                     AuthorRepository authorRepository, GenreRepository genreRepository, CommentRepository commentRepository) {
        var nameAuthors = List.of("Alex Pushkin", "Fedor Dostoevsky");
        nameAuthors.forEach(n -> libraryService.addAuthor(n).log("author saved " + n));
        var nameGenres = List.of("Horror", "Comdedy");
        nameGenres.forEach(g -> libraryService.addGenre(g).log("genre saved " + g));

        var levTolstoy = new Author();
        levTolstoy.setName("Lev Tolstoy");
        var levTolstoyAuthor = authorRepository.save(levTolstoy).block();
        log.info("Lev Tolstoy author " + levTolstoyAuthor.getName() + " ID "  + levTolstoyAuthor.getId());
        var drama = new Genre();
        drama.setName("Drama");
        var dramaGenre = genreRepository.save(drama).block();
        log.info("Drama genre " + dramaGenre.getName() + " ID " + dramaGenre.getId());
        var book = new Book();
        book.setTitle("Peace and War");
        book.setAuthors(List.of(levTolstoyAuthor));
        book.setGenres(List.of(dramaGenre));

        var savedBook = bookRepository.save(book).block();
        log.info("Saved book ID " + book.getId() + " authors " + savedBook.getAuthors() + " genres " + savedBook.getGenres());
        var comment = new Comment();
        comment.setBook(book);
        comment.setTitle("Test Comment");
        commentRepository.save(comment).block();

        return savedBook;
    }
	
	@Bean
	MongoClient mongoClient() {
		
		ConnectionString connectionString = new ConnectionString("mongodb://localhost");

		MongoClientSettings.Builder builder = MongoClientSettings.builder()
				.applyConnectionString(connectionString);
				
				//.add(ClusterSettings.builder().applyConnectionString(connectionString))
				
				//.connectionPoolSettings(ConnectionPoolSettings.builder().applyConnectionString(connectionString).build())
				//.serverSettings(ServerSettings.builder().applyConnectionString(connectionString).build())
				//.credentialList(connectionString.getCredentialList())
				//.sslSettings(SslSettings.builder().applyConnectionString(connectionString).build())
				//.socketSettings(SocketSettings.builder().applyConnectionString(connectionString).build());

		MongoClientSettings settings = builder.codecRegistry(MongoClients.getDefaultCodecRegistry()).build();

		return MongoClients.create(settings);
	}
//	
	@Bean
	ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient, MappingMongoConverter mappingMongoConverter) {
		return new ReactiveMongoTemplate(new SimpleReactiveMongoDatabaseFactory(mongoClient, "test"),
				mappingMongoConverter);
	}
}
