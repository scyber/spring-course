package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;




@SpringBootApplication
@EnableAutoConfiguration
public class QuizApplication {

	public static void main(String[] args){
		SpringApplication.run(QuizApplication.class, args);
	}

}
