package ru.otus;

import com.github.cloudyrock.spring.v5.EnableMongock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ru.otus.domain.Author;
import ru.otus.repository.AuthorRepository;


@EnableMongock
@EnableMongoRepositories
@SpringBootApplication
public class LibraryApplication {


	public static void main(String[] args) throws InterruptedException {

	    ApplicationContext context = SpringApplication.run(LibraryApplication.class, args);

//		AuthorRepository repository = context.getBean(AuthorRepository.class);
//
//		repository.save(new Author("Dostoevsky"));
//
//		Thread.sleep(3000);
//
//		System.out.println("\n\n\n----------------------------------------------\n\n");
//		System.out.println("Авторы в БД:");
//		repository.findAll().forEach(p -> System.out.println(p.getName()));
//		System.out.println("\n\n----------------------------------------------\n\n\n");
	}

}
