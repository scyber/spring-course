package ru.otus.converters;


import org.springframework.stereotype.Component;
import ru.otus.model.Genre;

@Component
public class GenreConverter {

    public String convert(Genre genre){
        return genre.getId() + " | " + genre.getName();
    }
}
