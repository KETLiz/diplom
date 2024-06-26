package ru.gb.diplom.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.diplom.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
