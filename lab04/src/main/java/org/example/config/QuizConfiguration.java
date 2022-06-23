package org.example.config;


import org.example.services.ConsoleIOServiceI;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
@PropertySource("classpath:application.properties")
public class QuizConfiguration {

    @Bean
    public ConsoleIOServiceI consoleIOService(){
        return new ConsoleIOServiceI(System.in,System.out);
    }

    @Bean
    public MessageSource messageSource(){
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        resourceBundleMessageSource.setBasenames("i18/quiz");
        return resourceBundleMessageSource;
    }

}
