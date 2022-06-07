package org.example.quiz;


import java.io.IOException;
import java.util.Map;
import org.example.quiz.config.QuizConfiguration;
import org.example.quiz.domain.Answer;
import org.example.quiz.domain.Question;
import org.example.quiz.repository.AnswerRepository;
import org.example.quiz.repository.QuestionRepository;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;


@ComponentScan
public class Application {


    public static void main(String args[]) throws IOException {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

        AnswerRepository answerRepository = context.getBean(AnswerRepository.class);
        QuestionRepository questionRepository = context.getBean(QuestionRepository.class);
        Map<Long,Question> rawQuestions = questionRepository.getQuestions();

        rawQuestions.values().forEach(q -> {
            System.out.println("Question " + q.getContext());
            System.out.println("-- Answers --");
            q.getAnswersList().forEach(id -> {
                try {
                    Answer a = answerRepository.findById(id);
                    System.out.println( a.getContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        });




    }


}
