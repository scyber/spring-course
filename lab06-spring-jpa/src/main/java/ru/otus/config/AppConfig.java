package ru.otus.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import ru.otus.services.IOService;
import ru.otus.services.IOServiceStreams;

@Configuration
public class AppConfig {

    @Bean
    public IOService ioServiceStreams(){
        return new IOServiceStreams( System.in, System.out);
    }
    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setBasenames("i18/library");
        return resourceBundleMessageSource;
    }
}
