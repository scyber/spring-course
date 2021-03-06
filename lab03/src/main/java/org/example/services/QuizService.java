package org.example.services;

import org.example.domain.Question;
import org.example.domain.Student;
import org.example.exceptions.QuizRuntimExecption;
import org.example.repository.AnswerRepository;
import org.example.repository.QuestionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class QuizService {

    private QuestionRepository questionRepository;
    private AggregateAnswerService aggregateAnswerService;
    private ValidateService validateService;
    private ConsoleIOServiceI consoleIOService;
    private AnswerRepository answerRepository;
    private PropertiesService propertiesService;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

    public QuizService(QuestionRepository questionRepository,
                       AnswerRepository answerRepository,
                       ConsoleIOServiceI consoleIOService,
                       PropertiesService scorePropertiesService,
                       AggregateAnswerService aggregateAnswerService,
                       ValidateService validateService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.consoleIOService = consoleIOService;
        this.propertiesService = scorePropertiesService;
        this.aggregateAnswerService = aggregateAnswerService;
        this.validateService = validateService;
    }

    public void run(){
        var firstName = consoleIOService.resStringWithPrompt("Enter First Name");
        var lastName = consoleIOService.resStringWithPrompt("Enter Last Name");
        var student = new Student(firstName,lastName);
        for(Question question: questionRepository.getItems().values()) {
            consoleIOService.outputString(question.getContext());
            question.getAnswersList().forEach(a -> {
                try {
                    consoleIOService.outputString(answerRepository.findById(a).get().getContext());
                } catch (Exception e) {
                    LOGGER.warn("Could not find Answer by ID {}",a);
                    throw new QuizRuntimExecption("Could not find Answer by ID {} " , e);
                }
            });

            List<String> rawAnswers = consoleIOService.readListWithPrompt(propertiesService.getPromptAnswer());
            aggregateAnswerService.mapResults(question.getId(), rawAnswers);
        }
        validateService.validate(aggregateAnswerService.getAggResultsMap());
        if(validateService.provideResults() >= propertiesService.getScoreValue()) {
            consoleIOService.outputString(student.getFirstName() + " " + student.getLastName() + " " + propertiesService.getScoreGreeting()  + "  " + validateService.provideResults() );
        } else {
            consoleIOService.outputString(propertiesService.getFailGreeting() + " " + validateService.provideResults() + " " + propertiesService.getScoreBelow() + " " + propertiesService.getScoreValue() );
        }

    }

}
