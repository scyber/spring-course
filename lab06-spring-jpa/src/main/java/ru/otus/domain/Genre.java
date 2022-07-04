package ru.otus.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "GENRES")
@NamedEntityGraph(name = "book-genre-entity-graph", attributeNodes = {@NamedAttributeNode("BOOK")})
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @OneToOne(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id")
    private Long id;

    @Column(name = "genre_name")
    private String genreName;

}
