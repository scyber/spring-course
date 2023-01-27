package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {

    @Id
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private String title;

    //@DocumentReference//(lookup = "{ '_id' : ?#{#target} }", collection = "authors" )
    private List<Author> authors;

    //@DocumentReference//(collection = "genres", db = "test", lookup = "{ '_id' : ?#{#gerne.id} }")
    private List<Genre> genres;

    public Book(String title) {
        this.title = title;
    }

}
