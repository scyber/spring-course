package org.example.repository;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.example.domain.Answer;
import org.example.exceptions.TransferException;
import org.example.resources.AnswerResourceImpl;
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
public class AnswerTransfer implements TransferService<Answer>{
    private Resource resource;
    private static final Logger LOGGER = LoggerFactory.getLogger(AnswerTransfer.class);
    private Map<Long,Answer> answers;

    public AnswerTransfer(AnswerResourceImpl answerResource) {
        this.resource = answerResource.getResource();
    }

    public Map<Long,Answer> getAnswers(){return this.answers;}

    @Override
    public Map<Long,Answer> transfer() {
        Map<Long,Answer> result = new HashMap<>();
        final ColumnPositionMappingStrategy<Answer> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Answer.class);
        strategy.setColumnMapping(new String[]{"id","context"});
        try(Reader reader = Files.newBufferedReader(Paths.get(this.resource.getURI()))) {
            CsvToBean<Answer> rawAnswers = new CsvToBeanBuilder<Answer>(reader).withSeparator(',').withMappingStrategy(strategy).build();
            rawAnswers.stream().collect(Collectors.toList()).stream().forEach(answer -> {
                result.put(answer.getId(),answer);
            });
        } catch (Exception e){
            LOGGER.error("Could not get Resource path ", this.resource, e);
            throw new TransferException("Could not get resource path ", e);
        }
        return result;
    }
}
