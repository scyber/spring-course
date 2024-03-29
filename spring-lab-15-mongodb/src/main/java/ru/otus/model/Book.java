package ru.otus.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
@Data
public class Book {

    @Id
    private String id;

    private String title;

    private List<Author> authors;

    private List<Genre> genres;

    public Book(String title) {
        this.title = title;
    }
}
