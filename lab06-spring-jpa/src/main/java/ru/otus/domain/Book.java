package ru.otus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Book {

    private Long id;
    private String name;
    private Long authorId;
    private Long genreId;
}
