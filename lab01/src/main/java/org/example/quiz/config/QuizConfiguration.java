package org.example.quiz.config;

import java.io.IOException;

import org.example.quiz.repository.AnswerRepository;
import org.example.quiz.repository.QuestionRepository;
import org.example.quiz.resources.AnswerResourceImpl;
import org.example.quiz.resources.QuestionResourceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuizConfiguration {


    @Bean
    public QuestionResourceImpl questionResource() {
        return new QuestionResourceImpl();
    }

    @Bean
    public AnswerResourceImpl answerResource() {
        return new AnswerResourceImpl();
    }

    @Bean
    public AnswerRepository answerRepository() {
        return new AnswerRepository();
    }

    @Bean
    public QuestionRepository questionRepository() throws IOException {
        return new QuestionRepository();
    }
}
