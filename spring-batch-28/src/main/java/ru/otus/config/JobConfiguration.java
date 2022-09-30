package ru.otus.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.data.MongoItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import ru.otus.model.Book;
import ru.otus.repository.BookRepositoryCustom;

@Configuration
public class JobConfiguration {

    private static final int CHUNK_SIZE = 5;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobConfiguration.class);
    private static final String OUTPUT_BOOKS_FILE_NAME = "input-books-file";

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private BookRepositoryCustom bookRepositoryCustom;

    @StepScope
    @Bean
    public MongoItemReader<Book> reader(){
        MongoItemReader<Book> reader = new MongoItemReader<>();
        reader.setName("MongoBookReader");
        return reader;
    }

    @StepScope
    @Bean
    public FlatFileItemWriter<Book> writer(@Value("#{jobParameters['" + OUTPUT_BOOKS_FILE_NAME +"']}") String outputFileName){
        var fileItemWriter = new FlatFileItemWriterBuilder<Book>()
                              .name("BookOutputWriter")
                              .resource(new FileSystemResource(outputFileName))
                              .lineAggregator(new DelimitedLineAggregator<>())
                              .build();

        return fileItemWriter;
    }
}
