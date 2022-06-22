package org.example;

import org.example.repository.QuestionRepository;
import org.example.services.PropertiesService;
import org.example.services.QuizService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


import java.util.Locale;


@SpringBootApplication
@EnableAutoConfiguration
public class QuizApplication {

	public static void main(String[] args){
		ApplicationContext ctx = SpringApplication.run(QuizApplication.class, args);
		PropertiesService scorePropertiesService = ctx.getBean(PropertiesService.class);
		System.out.println(scorePropertiesService.getScoreValue());

		System.out.println(Locale.getDefault());
		QuizService quizService = ctx.getBean(QuizService.class);
		QuestionRepository questionRepository = ctx.getBean(QuestionRepository.class);

		questionRepository.getQuestions().values().forEach(q -> System.out.println(q.getContext()));


	}

}
