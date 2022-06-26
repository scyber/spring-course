package org.example.quiz.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.example.quiz.domain.Question;
import org.springframework.stereotype.Component;

@Component
public class QuestionRepository implements QuizRepository<Question>{

    private Map<Long,Question> questionMap = new HashMap<>();



    public QuestionRepository(QuestionTransfer questionTransfer) {
        List<Question> questions = questionTransfer.getQuestions();
        questions.forEach(q -> questionMap.put(q.getId(),q));
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
