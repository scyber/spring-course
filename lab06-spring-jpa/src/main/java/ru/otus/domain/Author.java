package ru.otus.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@NoArgsConstructor
public class Author {

    private Long id;
    private String firstName;
    private String secondName;
    private String lastName;


}
