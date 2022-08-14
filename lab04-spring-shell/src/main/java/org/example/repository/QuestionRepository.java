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


    public Map<Long,Question> getQuestions(){
        return this.questions;
    }

    @Override
    public Optional<Question> findById(Long id){
        return Optional.of(questions.get(id));
    }


}
