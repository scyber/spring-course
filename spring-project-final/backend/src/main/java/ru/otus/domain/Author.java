package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {

    @Id
    private String id;

    private String name;

    public Author(String name) {
        this.name = name;
    }

}
