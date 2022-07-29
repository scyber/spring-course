package ru.otus.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "comments")
@Getter
@Setter
public class Comment {

    @Id
    private long id;

    @DBRef
    private Book book;

    private String title;
}
