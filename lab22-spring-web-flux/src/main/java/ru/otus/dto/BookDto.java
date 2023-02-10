package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private String id;
    private String title;
    private List<Author> authors;
    private List<Genre> genres;


    public BookDto fromDomainObject(Book book) {
        return new BookDto(book.getId(), book.getTitle(), book.getAuthors(), book.getGenres());
    }

    public Book toDomainObject() {
        var book = new Book();
        book.setTitle(title);
        book.setId(id);
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }

}
