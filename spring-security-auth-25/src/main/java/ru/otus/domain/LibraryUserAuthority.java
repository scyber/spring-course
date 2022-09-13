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

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String authority;
}
