package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoConfiguration
@SpringBootApplication
public class LibraryApplication {

	public static void main(String[] args) {
	 SpringApplication.run(LibraryApplication.class, args);
	}

}
