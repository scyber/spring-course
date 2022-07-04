package ru.otus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Optional;


@Data
@NoArgsConstructor
@Entity
@Table(name = "AUTHORS")
@NamedEntityGraph(name = "book-author-entity-graph", attributeNodes = {@NamedAttributeNode("BOOK")})
public class Author {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @OneToOne(targetEntity = Book.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private Long id;

    @Column(name = "author_first_name", nullable = false)
    private String firstName;

    @Column(name = "author_second_name")
    private String secondName;

    @Column(name = "author_last_name")
    private String lastName;


}
