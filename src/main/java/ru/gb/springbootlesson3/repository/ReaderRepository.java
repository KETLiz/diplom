package ru.gb.springbootlesson3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Reader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    Optional<Reader> findByName(String name);

}
