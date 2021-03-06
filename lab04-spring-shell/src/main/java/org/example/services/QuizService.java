package org.example.services;

import org.example.domain.Question;
import org.example.domain.Student;
import org.example.repository.AnswerRepository;
import org.example.repository.QuestionRepository;

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
    private ConsoleIOService consoleIOService;
    private AnswerRepository answerRepository;
    private PropertiesService propertiesService;
    private Student student;

    private static final Logger LOGGER = LoggerFactory.getLogger(QuizService.class);

    public QuizService(QuestionRepository questionRepository,
                       AnswerRepository answerRepository,
                       ConsoleIOService consoleIOService,
                       PropertiesService scorePropertiesService) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.consoleIOService = consoleIOService;
        this.propertiesService = scorePropertiesService;
        this.aggregateAnswerService = new AggregateAnswerService(answerRepository);
        this.validateService = new ValidateService(questionRepository,consoleIOService);
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void run(){
        for(Question question: questionRepository.getQuestions().values()) {
            consoleIOService.outputString(question.getContext());
            question.getAnswersList().forEach(a -> {
                try {
                    consoleIOService.outputString(answerRepository.findById(a).get().getContext());
                } catch (IOException e) {
                    LOGGER.warn("Could not find Answer by ID {}", a);
                    e.printStackTrace();
                }
            });

            List<String> rawAnswers = consoleIOService.readListWithPrompt(propertiesService.getPromptAnswer());
            aggregateAnswerService.mapResults(question.getId(), rawAnswers);
        }
        validateService.validate(aggregateAnswerService.getAggResultsMap());
        this.student.setScore(validateService.provideResults());
        if(validateService.provideResults() >= propertiesService.getScoreValue()) {
            consoleIOService.outputString(this.student.getFirstName() + " " + this.student.getLastName() + " " + propertiesService.getScoreGreeting()  + "  " + validateService.provideResults() );
        } else {
            consoleIOService.outputString(propertiesService.getFailGreeting() + " " + this.student.getFirstName() + " " + this.student.getLastName() + " " + validateService.provideResults() + " " + propertiesService.getScoreBelow() + " " + propertiesService.getScoreValue() );
        }

    }
    public Double getScore(){
       return this.student.getScore();
    }

}
