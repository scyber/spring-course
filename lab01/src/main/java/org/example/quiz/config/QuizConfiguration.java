package org.example.quiz.config;


import org.example.quiz.services.ConsoleIOServiceI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class QuizConfiguration {

    @Bean
    public ConsoleIOServiceI consoleIOService(){
        return new ConsoleIOServiceI(System.in,System.out);
    }


}
