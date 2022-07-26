package ru.otus.converters;

import org.springframework.stereotype.Component;
import ru.otus.domain.Book;

@Component
public class BookConverter {

    public String convert(Book book) {
        String result = book.getId() + " | " + book.getTitle() ;
        return result;
    }
}
