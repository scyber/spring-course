package org.example.services;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Optional;


@Service
public class PropertiesService {
    private MessageSource messageSource;

    public PropertiesService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Value("${score.pass}")
    private Double scoreValue;


    public Double getScoreValue() {
        return this.scoreValue;
    }

    public String getScoreGreeting(){
        return messageSource.getMessage("score.greeting",null, Locale.getDefault());
    }
    public String getFailGreeting(){
        return messageSource.getMessage("score.fail", null, Locale.getDefault());
    }
    public String getPromptAnswer(){
        return messageSource.getMessage("score.question.prompt", null, Locale.getDefault());
    }
    public String getScoreBelow(){
        return messageSource.getMessage("score.below", null, Locale.getDefault());
    }
}
