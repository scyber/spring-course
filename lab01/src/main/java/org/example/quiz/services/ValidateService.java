package org.example.quiz.services;

import org.example.quiz.repository.QuestionRepository;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ValidateService {

    private QuestionRepository questionRepository;
    private ConsoleIOServiceI ioService;
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private Map<Long,Boolean> validResults = new HashMap<>();

    public ValidateService(QuestionRepository questionRepository, ConsoleIOServiceI consoleIOService) {
        this.questionRepository = questionRepository;
        this.ioService = consoleIOService;
    }

    public void validate(Map<Long,List<Long>> incomingResults){
        incomingResults.forEach((k,v) -> {
            List<Long> correctValues = questionRepository.getQuestions().get(k).getCorrectAnswers();
            if(correctValues.containsAll(v) & correctValues.size() == v.size()){
                validResults.put(k,true);
            }
        });

    }
    public double provideResults(){
        return (double) validResults.size()/questionRepository.getQuestions().size();
    }
    public void outputScore(){
        ioService.outputString(df.format(provideResults()));
    }
}
