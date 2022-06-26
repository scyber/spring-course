package org.example.domain;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;

    private Double score;

    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
