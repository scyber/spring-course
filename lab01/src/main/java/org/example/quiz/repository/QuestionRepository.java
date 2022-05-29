package org.example.quiz.repository;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.example.quiz.domain.Question;
import org.example.quiz.resources.QuestionResourceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;

public class QuestionRepository implements QuizRepository<Question>{

    private Map<Long,Question> questionMap = new HashMap<>();

    @Autowired
    private QuestionResourceImpl questionResource;

    public QuestionRepository() {

    }

    public Map<Long,Question> getQuestions() throws IOException {
        processQuestions();
        return this.questionMap;
    }


    private void processQuestions() throws IOException {
        Resource resource = questionResource.getResource();
        Reader reader = Files.newBufferedReader(Paths.get(resource.getURI()));;
        final ColumnPositionMappingStrategy<Question> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Question.class);
        strategy.setColumnMapping(new String[]{"id","context","answersList", "correctAnswers"});
        CsvToBean<Question> rawquestions = new CsvToBeanBuilder<Question>(reader).withSeparator(',').withMappingStrategy(strategy).build();
        rawquestions.forEach(x -> questionMap.put(x.getId(),x));
    }

    @Override
    public Question findById(Long id) throws IOException {
        return getQuestions().get(id);

    }

    @Override
    public List<Question> findByQuestionId(Long id) {
        return null;
    }
}
