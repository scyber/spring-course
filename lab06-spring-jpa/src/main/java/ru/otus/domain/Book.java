package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOKS")
@NamedEntityGraph(name = "books-genre-entity-graph", attributeNodes = {@NamedAttributeNode("GENRE")})
@NamedEntityGraph(name = "book-authors-entity-graph", attributeNodes = {@NamedAttributeNode("AUTHORS")})
public class Book {
    @Id
    private Long id;

    @Column(name = "book_name", nullable = false, unique = true)
    private String name;

    @OneToOne(targetEntity = Author.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Author author;

    @OneToOne(targetEntity = Genre.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Genre genres;

    public Book(String name, Author author, Genre genres) {
        this.name = name;
        this.author = author;
        this.genres = genres;
    }
}
