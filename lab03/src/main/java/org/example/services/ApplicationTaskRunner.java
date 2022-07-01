package org.example.services;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationTaskRunner implements ApplicationRunner {
    private QuizService quizService;

    public ApplicationTaskRunner(QuizService quizService) {
        this.quizService = quizService;
    }

    @Override
    public void run(ApplicationArguments args) {
        quizService.run();
    }
}
