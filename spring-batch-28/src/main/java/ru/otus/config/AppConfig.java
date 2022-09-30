package ru.otus.config;


import com.github.cloudyrock.mongock.Mongock;
import com.github.cloudyrock.mongock.SpringMongockBuilder;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.otus.services.IOService;
import ru.otus.services.IOServiceStreams;

@Configuration
public class AppConfig {

    static final String CHANGELOGS_PACKAGE = "ru.otus.mongock";

    @Bean
    public Mongock mongock(MongoProps mongoProps, MongoClient mongoClient) {
        return new SpringMongockBuilder(mongoClient, mongoProps.getDatabase(), CHANGELOGS_PACKAGE)
                .build();
    }
    @Bean
    public IOService ioServiceStreams(){
        return new IOServiceStreams( System.in, System.out);
    }

}
