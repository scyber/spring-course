package org.example.repository;


import org.example.domain.Question;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

@Component
public class QuestionRepository implements QuizRepository<Question>{

    private Map<Long,Question> questions;



    public QuestionRepository(QuestionTransfer questionTransfer) {
        this.questions = questionTransfer.getQuestions();
    }

    @Override
    public Map<Long,Question> getItems(){
        return this.questions;
    }

    @Override
    public Optional<Question> findById(Long id){
        return Optional.of(questions.get(id));
    }



}
