package org.example.repository;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.domain.Question;
import org.example.exceptions.TransferException;
import org.example.resources.QuestionResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class QuestionTransfer implements TransferService<Question>{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionTransfer.class);
    private Resource resource;
    private Map<Long,Question> questions;

    public QuestionTransfer(QuestionResourceImpl questionResource) {
        this.resource = questionResource.getResource();
        this.questions = transfer();
    }

    public Map<Long,Question> getQuestions() {
        return this.questions;
    }

    @Override
    public Map<Long,Question> transfer() {
        Map<Long,Question> result = new HashMap<>();
        final ColumnPositionMappingStrategy<Question> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Question.class);
        strategy.setColumnMapping(new String[]{"id","context","answersList", "correctAnswers"});
        try (Reader reader = Files.newBufferedReader(Paths.get(resource.getURI()))) {
            CsvToBean<Question> rawQuestions = new CsvToBeanBuilder<Question>(reader).withSeparator(',').withMappingStrategy(strategy).build();
            rawQuestions.stream().collect(Collectors.toList()).stream().forEach(question -> result.put(question.getId(),question
            ));
        } catch (Exception e){
            LOGGER.error("Could not get read from resource ", resource, e);
            throw new TransferException("Could not read from resource ", e);
        }
        return result;
    }
}
