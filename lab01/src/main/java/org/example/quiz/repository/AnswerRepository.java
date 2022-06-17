package org.example.quiz.repository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import org.example.quiz.domain.Answer;



public class AnswerRepository implements QuizRepository<Answer> {

    private Map<Long,Answer> answers = new HashMap<>();

    public AnswerRepository(AnswerTransfer answerTransfer) {
        List<Answer> answersTransferred = answerTransfer.getAnswers();
        answersTransferred.forEach(a -> answers.put(a.getId(),a));
    }

    public Map<Long,Answer> getAnswers() {
        return this.answers;
    }


    @Override
    public Optional<Answer> findById(Long id) throws IOException {
        List<Answer> filteredAnswer = answers.values().stream().filter(a -> a.getId() == id).collect(Collectors.toList());
        return filteredAnswer.stream().findAny();
    }


    public Optional<Answer> findByContext(String context) {
       List<Answer> filteredByContext = answers.values().stream().filter(a -> a.getContext().equals(context)).collect(Collectors.toList());
       return filteredByContext.stream().findAny();
    }


}
