package org.example.quiz;

import org.example.quiz.domain.Question;
import org.example.quiz.domain.Student;
import org.example.quiz.repository.AnswerRepository;
import org.example.quiz.repository.AnswerTransfer;
import org.example.quiz.repository.QuestionRepository;
import org.example.quiz.repository.QuestionTransfer;
import org.example.quiz.resources.AnswerResourceImpl;
import org.example.quiz.resources.QuestionResourceImpl;
import org.example.quiz.services.AggregateAnswerService;
import org.example.quiz.services.ConsoleIOService;
import org.example.quiz.services.ValidateService;
import java.io.IOException;
import java.util.List;



public class DebugApp {
    public static void main(String args[]){
        AnswerResourceImpl answerResource = new AnswerResourceImpl();
        AnswerTransfer answerTransfer = new AnswerTransfer(answerResource);
        AnswerRepository answerRepository = new AnswerRepository(answerTransfer);


        QuestionResourceImpl questionResource = new QuestionResourceImpl();
        QuestionTransfer questionTransfer = new QuestionTransfer(questionResource);
        QuestionRepository questionRepository = new QuestionRepository(questionTransfer);
        AggregateAnswerService aggregateAnswerService = new AggregateAnswerService(answerRepository);
        ConsoleIOService ioService = new ConsoleIOService();

        var firstName = ioService.resStringWithPrompt("Enter First Name");
        var lastName = ioService.resStringWithPrompt("Enter Last Name");

        var student = new Student(firstName,lastName);

        for(Question question: questionRepository.getQuestions().values()) {
            ioService.outputString(question.getContext());
            question.getAnswersList().forEach(a -> {
                try {
                    ioService.outputString(answerRepository.findById(a).get().getContext());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            List<String> rawAnswers = ioService.readResultsWithPrompt("Please Enter Correct Answers with space ");
            aggregateAnswerService.mapResults(question.getId(), rawAnswers);
        }
        ValidateService validateService = new ValidateService(questionRepository,ioService);
        validateService.validate(aggregateAnswerService.getAggResultsMap());
        ioService.outputString( student.getFirstName() + " " + student.getLastName() + " Your score is ");
        validateService.outputScore();
    }
}
