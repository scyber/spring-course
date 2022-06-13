package org.example.quiz.config;

import java.io.IOException;

import org.example.quiz.repository.AnswerRepository;
import org.example.quiz.repository.AnswerTransfer;
import org.example.quiz.repository.QuestionRepository;
import org.example.quiz.repository.QuestionTransfer;
import org.example.quiz.resources.AnswerResourceImpl;
import org.example.quiz.resources.QuestionResourceImpl;

import org.example.quiz.services.ConsoleIOService;
import org.example.quiz.services.QuizService;
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
    public AnswerRepository answerRepository(AnswerTransfer answerTransfer) {
        return new AnswerRepository(answerTransfer);
    }

    @Bean
    public QuestionRepository questionRepository(QuestionTransfer questionTransfer){
        return new QuestionRepository(questionTransfer);
    }
    @Bean
    public AnswerTransfer answerTransfer(AnswerResourceImpl answerResource){
        return new AnswerTransfer(answerResource);
    }
    @Bean
    public QuestionTransfer questionTransfer(QuestionResourceImpl questionResource){
        return new QuestionTransfer(questionResource);
    }
    @Bean
    public ConsoleIOService consoleIOService(){
        return new ConsoleIOService();
    }
    @Bean
    public QuizService quizService(QuestionRepository questionRepository,
                                   AnswerRepository answerRepository,
                                   ConsoleIOService consoleIOService){
      return new QuizService(questionRepository,answerRepository,consoleIOService);
    }
}
