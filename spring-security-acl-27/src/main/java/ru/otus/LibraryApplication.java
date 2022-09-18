package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories("ru.otus.repository")
public class LibraryApplication {

	public static void main(String[] args) {
	 SpringApplication.run(LibraryApplication.class, args);
	}

}
