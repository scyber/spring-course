package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "genres")
public class Genre {

    @Id
    @MongoId(targetType = FieldType.OBJECT_ID)
    private String id;

    private String name;

    public Genre(String name) {
        this.name = name;
    }

//    @Override
//    public String toString() {
//        return name;
//    }
}
