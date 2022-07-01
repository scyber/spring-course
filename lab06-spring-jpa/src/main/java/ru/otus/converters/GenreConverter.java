package ru.otus.converters;


import org.springframework.stereotype.Component;
import ru.otus.domain.Genre;

@Component
public class GenreConverter {

    public String convert(Genre genre){
        return genre.getId() + " | " + genre.getGenreName();
    }
}
