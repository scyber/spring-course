package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Book {

    @Id
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "author_id")
    private Author author;

    @Column(name = "genre_id")
    private Genre genre;
}
