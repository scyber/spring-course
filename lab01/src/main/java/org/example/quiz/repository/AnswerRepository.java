package org.example.quiz.repository;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.example.quiz.domain.Answer;
import org.example.quiz.resources.AnswerResourceImpl;
import org.example.quiz.resources.QuizResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


public class AnswerRepository implements QuizRepository<Answer> {

    public void setAnswerResource(AnswerResourceImpl answerResource) {
        this.answerResource = answerResource;
    }

    //@Autowired
    private AnswerResourceImpl answerResource;

    private Map<Long,Answer> answers = new HashMap<>();

    public AnswerRepository() {
    }

    public Map<Long,Answer> getAnswers() throws IOException {
        processAnswers();
        return this.answers;
    }

    private void processAnswers() throws IOException {
        Resource resource = answerResource.getResource();
        Reader reader = Files.newBufferedReader(Paths.get(resource.getURI()));;
        final ColumnPositionMappingStrategy<Answer> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Answer.class);
        strategy.setColumnMapping(new String[]{"id","context"});
        CsvToBean<Answer> rawanswers = new CsvToBeanBuilder<Answer>(reader).withSeparator(',').withMappingStrategy(strategy).build();
        rawanswers.forEach(x -> answers.put(x.getId(),x));
    }



    @Override
    public Answer findById(Long id) throws IOException {
       return getAnswers().get(id);
    }

    @Override
    public List<Answer> findByQuestionId(Long id) {
        return null;
    }



}
