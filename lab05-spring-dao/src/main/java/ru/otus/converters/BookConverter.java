package ru.otus.converters;

import org.springframework.stereotype.Component;
import ru.otus.dao.AuthorDao;
import ru.otus.dao.GenreDao;
import ru.otus.domain.Book;

@Component
public class BookConverter {
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    public BookConverter(AuthorDao authorDao, GenreDao genreDao) {
        this.authorDao = authorDao;
        this.genreDao = genreDao;
    }

    public String convert(Book book) {
        String result = book.getId() + " | " + book.getName() + " | " + authorDao.findById(book.getAuthorId()).getFirstName() +
                 " " + authorDao.findById(book.getAuthorId()).getLastName() + " | " + genreDao.findById(book.getGenreId()).getGenreName();
        return result;
    }
}
