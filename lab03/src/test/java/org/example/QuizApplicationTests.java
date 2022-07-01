package org.example;

import org.example.resources.FileNameAnswersProvider;
import org.example.services.ApplicationTaskRunner;
import org.example.services.PropertiesService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;


import java.util.Locale;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class QuizApplicationTests {
    static {
        Locale.setDefault(Locale.ENGLISH);
    }

    @MockBean
    ApplicationTaskRunner applicationTaskRunner;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PropertiesService propertiesService;

    @Test
    void contextLoads() {
    }

    @Test
    void testResourceBoundle() {

        FileNameAnswersProvider fileNameAnswersProvider = new FileNameAnswersProvider(messageSource, Optional.of(Locale.FRANCE));
        Assertions.assertEquals("en_answers.csv", fileNameAnswersProvider.provide());
    }

    @Test
    void testRunnerApplicationTask() {
        Mockito.verify(applicationTaskRunner, Mockito.times(1)).run(any());
    }

    @Test
    void testScoreGreeting(){
        String greeting = messageSource.getMessage("score.greeting", null,Locale.getDefault());
        String failGreeting = messageSource.getMessage("score.fail", null, Locale.getDefault());
        String promptAnswer = messageSource.getMessage("score.question.prompt", null, Locale.getDefault());
        Assertions.assertTrue(!greeting.intern().isEmpty());
        Assertions.assertNotNull(failGreeting);
        Assertions.assertNotNull(promptAnswer);
        Assertions.assertEquals(0.5, propertiesService.getScoreValue());
    }


}
