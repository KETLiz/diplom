package ru.gb.springbootlesson3.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.springbootlesson3.repository.BookRepository;

import java.time.LocalDateTime;
@Entity
@Data
@Table
@NoArgsConstructor
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private long idReader;
    @Column
    private long idBook;
    @Column
    private LocalDateTime issuedAt;
    @Column
    private LocalDateTime returnedAt;

    public Issue(long idReader, long idBook){
        this.idBook = idBook;
        this.idReader = idReader;
        issuedAt = LocalDateTime.now();
        returnedAt = null;
    }

    public void setTimeReturn(LocalDateTime returnedAt) {
        this.returnedAt = returnedAt;
    }

}
