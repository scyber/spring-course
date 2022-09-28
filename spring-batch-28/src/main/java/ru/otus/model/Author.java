package ru.otus.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
@Data
public class Author {

    @Id
    private String id;

    private String name;

    public Author(String name) {
        this.name = name;
    }

}
