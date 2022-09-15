package ru.otus.domain;

import lombok.Getter;
import javax.persistence.*;


@Entity
@Table(name = "authorities")
@Getter
public class LibraryUserAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private LibraryUser user;

    private String authority;
}
