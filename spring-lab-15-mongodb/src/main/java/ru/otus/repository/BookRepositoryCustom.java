package ru.otus.repository;

import ru.otus.model.Author;
import ru.otus.model.Genre;

import java.util.List;

public interface BookRepositoryCustom {
    List<Author> getAuthorsByBookId(String bookId);
    List<Genre> getGenresByBookId(String bookId);
    void updateBookTitleById(String id, String title);

}
