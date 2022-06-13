package org.example.quiz.services;

import org.example.quiz.domain.Question;
import org.example.quiz.domain.Student;
import org.example.quiz.repository.AnswerRepository;
import org.example.quiz.repository.QuestionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.util.List;


public class QuizService {

    private QuestionRepository questionRepository;
    private AggregateAnswerService aggregateAnswerService;
    private ValidateService validateService;
    private ConsoleIOService consoleIOService;
    private AnswerRepository answerRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

    public QuizService(QuestionRepository questionRepository,
                       AnswerRepository answerRepository,
                       ConsoleIOService consoleIOService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.consoleIOService = consoleIOService;
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

            List<String> rawAnswers = consoleIOService.readResultsWithPrompt("Please Enter Correct Answers with space ");
            aggregateAnswerService.mapResults(question.getId(), rawAnswers);
        }
        validateService.validate(aggregateAnswerService.getAggResultsMap());
        consoleIOService.outputString( student.getFirstName() + " " + student.getLastName() + " Your score is ");
        validateService.outputScore();

    }
}
