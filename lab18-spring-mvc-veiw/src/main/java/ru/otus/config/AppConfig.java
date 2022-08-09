package ru.otus.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.services.IOService;
import ru.otus.services.IOServiceStreams;

import javax.servlet.ServletContext;

@Configuration
public class AppConfig {

    @Autowired
    ServletContext servletContext;

    @Bean
    public IOService ioServiceStreams() {
        return new IOServiceStreams(System.in, System.out);
    }

}
