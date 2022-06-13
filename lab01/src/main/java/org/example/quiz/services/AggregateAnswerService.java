package org.example.quiz.services;

import org.example.quiz.domain.Answer;
import org.example.quiz.repository.AnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AggregateAnswerService {

    private AnswerRepository answerRepository;
    private Map<Long,List<Long>> aggResultsMap = new HashMap<>();

    public AggregateAnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    public void mapResults(Long questionId, List<String> answers ){

        List<Long> answersIds = answers.stream().map(c ->
                answerRepository.findByContext(c).orElse(new Answer()).getId()
        ).collect(Collectors.toList());
        aggResultsMap.put(questionId,answersIds);
    }
    public Map<Long,List<Long>> getAggResultsMap(){
        return this.aggResultsMap;
    }
}
