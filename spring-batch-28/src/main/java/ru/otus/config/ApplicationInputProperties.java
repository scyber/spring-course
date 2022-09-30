package ru.otus.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties("app")
public class ApplicationInputProperties {
    private String inputBooksFile;
    private String inputAuthorsFile;
    private String inputGenresFile;
}
