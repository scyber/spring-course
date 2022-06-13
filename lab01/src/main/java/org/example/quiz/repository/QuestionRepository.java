package org.example.quiz.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.quiz.domain.Question;

public class QuestionRepository implements QuizRepository<Question>{

    private Map<Long,Question> questionMap = new HashMap<>();


    private QuestionTransfer questionTransfer;


    public QuestionRepository(QuestionTransfer questionTransfer) {
        List<Question> questions = questionTransfer.transfer();
        questions.forEach(q -> questionMap.put(q.getId(),q));
        this.questionTransfer = questionTransfer;
    }


    public Map<Long,Question> getQuestions(){
        return this.questionMap;
    }

    @Override
    public Optional<Question> findById(Long id){
        List<Question> filteredQuestion = questionMap.values().stream().filter(q -> q.getId() == id).collect(Collectors.toList());
        return filteredQuestion.stream().findAny();
    }


}
