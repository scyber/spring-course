package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genres")
@Getter
@Setter
public class Genre {

    @Id
    private String id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }

}
