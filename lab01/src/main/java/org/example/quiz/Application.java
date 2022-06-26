package org.example.quiz;

import org.example.quiz.services.QuizService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;



@ComponentScan
@Configuration
public class Application {


    public static void main(String args[]) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        QuizService quizService = context.getBean(QuizService.class);
        quizService.run();

    }


}
