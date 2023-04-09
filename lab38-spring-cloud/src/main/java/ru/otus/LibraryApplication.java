package ru.otus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration
@EnableFeignClients(basePackages = "ru.otus.clients")
@SpringBootApplication
@EnableJpaRepositories("ru.otus.repository")
public class LibraryApplication {

	public static void main(String[] args) {
	 SpringApplication.run(LibraryApplication.class, args);
	}

}
