package ru.otus.converters;

import org.springframework.stereotype.Component;
import ru.otus.model.Author;

@Component
public class AuthorConverter {

    public String convert(Author author){
        return  author.getId() + " | " + author.getName();
    }
}
