package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document(collection = "authors")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Author {

    @Id
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private String name;

    public Author(String name) {
        this.name = name;
    }


//    @Override
//    public String toString() {
//        return name;
//    }
}
