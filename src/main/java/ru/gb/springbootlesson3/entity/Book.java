package ru.gb.springbootlesson3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table

public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;

    public Book() {

    }
    public Book(String name) {
        this.name = name;
    }

}
