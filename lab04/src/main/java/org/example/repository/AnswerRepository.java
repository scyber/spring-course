package org.example.repository;

import org.example.domain.Answer;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


@Component
public class AnswerRepository implements QuizRepository<Answer> {

    private Map<Long,Answer> answers;

    public AnswerRepository(AnswerTransfer answerTransfer) {
        this.answers = answerTransfer.getAnswers();
    }

    public Map<Long,Answer> getAnswers() {
        return this.answers;
    }


    @Override
    public Optional<Answer> findById(Long id) throws IOException {
        return Optional.of(answers.get(id));
    }


    public Optional<Answer> findByContext(String context) {
       List<Answer> filteredByContext = answers.values().stream().filter(a -> a.getContext().equals(context)).collect(Collectors.toList());
       return filteredByContext.stream().findAny();
    }


}
