package org.example.quiz.repository;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.quiz.domain.Question;
import org.example.quiz.resources.QuestionResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionTransfer implements TransferService<Question>{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionTransfer.class);
    private Resource resource;
    private List<Question> questions;

    public QuestionTransfer(QuestionResourceImpl questionResource) {
        this.resource = questionResource.getResource();
        this.questions = transfer();
    }

    public List<Question> getQuestions() {
        return this.questions;
    }

    @Override
    public List<Question> transfer() {
        List<Question> result = new ArrayList<>();
        final ColumnPositionMappingStrategy<Question> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Question.class);
        strategy.setColumnMapping(new String[]{"id","context","answersList", "correctAnswers"});
        try (Reader reader = Files.newBufferedReader(Paths.get(resource.getURI()))) {
            CsvToBean<Question> rawQuestions = new CsvToBeanBuilder<Question>(reader).withSeparator(',').withMappingStrategy(strategy).build();
            result = rawQuestions.stream().collect(Collectors.toList());
        } catch (Exception e){
            LOGGER.error("Could not get reade from resource ", resource, e);
        }
        return result;
    }
}
