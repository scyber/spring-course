package ru.otus.converters;

import org.springframework.stereotype.Component;
import ru.otus.model.Book;

@Component
public class BookConverter {

    public String convert(Book book) {
        String result = book.getId() + " | " + book.getTitle() ;
        return result;
    }
}
