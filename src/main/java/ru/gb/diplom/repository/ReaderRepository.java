package ru.gb.diplom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.diplom.entity.Reader;

import java.util.Optional;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    Optional<Reader> findByName(String name);

}
