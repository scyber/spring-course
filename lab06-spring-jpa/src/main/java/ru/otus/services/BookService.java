package ru.otus.services;

import java.util.List;

public interface BookService {
    List<String> getAllBooks();
    String getBookById(long id);
    long addBook(String title, long authorId, long genreId );
    void delBook(long id);
    void updateBookNameById(long id, String name);
    List<String> getAllAuthors();
    long addAuthor(String name);
    void delAuthor(long authorId);
    List<String> getAllGenres();
    long addGenre(String genreName);
    void delGenre(long genreId);
}
