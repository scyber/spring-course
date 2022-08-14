package org.example.resources;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Optional;

@Component
public class FileNameQuestionsProvider implements ItemProvider {
    private MessageSource messageSource;
    private Locale locale;

    public FileNameQuestionsProvider(MessageSource messageSource, Optional<Locale> locale) {
        this.messageSource = messageSource;
        this.locale = locale.orElse(Locale.getDefault());
    }
    @Override
    public String provide(){
        String questions = messageSource.getMessage("questions", null, locale);
        return questions;
    }
}
