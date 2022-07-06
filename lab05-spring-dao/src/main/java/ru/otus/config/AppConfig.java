package ru.otus.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.services.IOService;
import ru.otus.services.IOServiceStreams;

@Configuration
public class AppConfig {
    @Bean
    public IOService ioServiceStreams(){
        return new IOServiceStreams( System.in, System.out);
    }
}
