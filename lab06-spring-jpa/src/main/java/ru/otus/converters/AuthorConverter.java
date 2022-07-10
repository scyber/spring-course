package ru.otus.converters;

import org.springframework.stereotype.Component;
import ru.otus.domain.Author;

@Component
public class AuthorConverter {

    public String convert(Author author){
        return  author.getId() + " | " + author.getName();
    }
}
