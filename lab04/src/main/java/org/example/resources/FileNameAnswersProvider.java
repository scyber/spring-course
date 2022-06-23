package org.example.resources;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import java.util.Locale;
import java.util.Optional;

@Component
public class FileNameAnswersProvider {
    private MessageSource messageSource;
    private Locale locale;

    public FileNameAnswersProvider(MessageSource messageSource, Optional<Locale> locale) {
        this.messageSource = messageSource;
        this.locale = locale.orElse(Locale.getDefault());
    }
    public String provide(){
        String answersFile = messageSource.getMessage("answers",null, locale);
        return answersFile;
    }
}
