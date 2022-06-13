package org.example.quiz.repository;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.quiz.domain.Answer;
import org.example.quiz.resources.AnswerResourceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;


import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerTransfer implements TransferService<Answer>{
    private Resource answerResource;
    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerTransfer.class);

    public AnswerTransfer(AnswerResourceImpl answerResource) {
        this.answerResource = answerResource.getResource();
    }


    @Override
    public List<Answer> transfer() {
        List<Answer> result = new ArrayList<>();
        final ColumnPositionMappingStrategy<Answer> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Answer.class);
        strategy.setColumnMapping(new String[]{"id","context"});

        try(Reader reader = Files.newBufferedReader(Paths.get(answerResource.getURI()))) {
            CsvToBean<Answer> rawanswers = new CsvToBeanBuilder<Answer>(reader).withSeparator(',').withMappingStrategy(strategy).build();
            result = rawanswers.stream().collect(Collectors.toList());
        } catch (Exception e){
            LOGGER.error("Could not get Resource path ", answerResource, e);
        }
        return result;
    }
}
