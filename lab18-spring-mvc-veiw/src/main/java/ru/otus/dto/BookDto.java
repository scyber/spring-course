package ru.otus.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.otus.domain.Author;
import ru.otus.domain.Book;
import ru.otus.domain.Genre;

import javax.validation.constraints.Size;
import java.util.List;

public class BookDto {

    private Long id;

    @Size(min = 2, max = 128, message = "{title-field-should-has-expected-size}")
    private String title;

    private List<Author> authors;

    private List<Genre> genres;

    public BookDto(){

    }
    public BookDto(Long id, String title, List<Author> authors, List<Genre> genres) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.genres = genres;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public Book toDomainObject(){
        var book = new Book();
        book.setId(id);
        book.setTitle(title);
        book.setAuthors(authors);
        book.setGenres(genres);
        return book;
    }
    public static BookDto fromDomainObject(Book book){
        return new BookDto(book.getId(), book.getTitle(), book.getAuthors(), book.getGenres());
    }
}
