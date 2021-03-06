package org.example.quiz.services;

import org.example.quiz.domain.Question;
import org.example.quiz.domain.Student;
import org.example.quiz.repository.AnswerRepository;
import org.example.quiz.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class QuizService {

    private QuestionRepository questionRepository;
    private AggregateAnswerService aggregateAnswerService;
    private ValidateService validateService;
    private ConsoleIOServiceI consoleIOService;
    private AnswerRepository answerRepository;
    private ScorePropertiesService scorePropertiesService;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

    public QuizService(QuestionRepository questionRepository,
                       AnswerRepository answerRepository,
                       ConsoleIOServiceI consoleIOService,
                       ScorePropertiesService scorePropertiesService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.consoleIOService = consoleIOService;
        this.scorePropertiesService = scorePropertiesService;
        this.aggregateAnswerService = new AggregateAnswerService(answerRepository);
        this.validateService = new ValidateService(questionRepository,consoleIOService);
    }

    public void run(){
        var firstName = consoleIOService.resStringWithPrompt("Enter First Name");
        var lastName = consoleIOService.resStringWithPrompt("Enter Last Name");
        var student = new Student(firstName,lastName);
        for(Question question: questionRepository.getQuestions().values()) {
            consoleIOService.outputString(question.getContext());
            question.getAnswersList().forEach(a -> {
                try {
                    consoleIOService.outputString(answerRepository.findById(a).get().getContext());
                } catch (IOException e) {
                    LOGGER.warn("Could not find Answer by ID {}",a);
                    e.printStackTrace();
                }
            });

            List<String> rawAnswers = consoleIOService.readListWithPrompt("Please Enter Correct Answers with space ");
            aggregateAnswerService.mapResults(question.getId(), rawAnswers);
        }
        validateService.validate(aggregateAnswerService.getAggResultsMap());
        if(validateService.provideResults() >= scorePropertiesService.getScoreValue()) {
            consoleIOService.outputString(student.getFirstName() + " " + student.getLastName() + " You have passed and your score " + validateService.provideResults() );
        } else {
            consoleIOService.outputString(" You have not passed your score  " + validateService.provideResults() + " below  " + scorePropertiesService.getScoreValue() );
        }

    }
}
