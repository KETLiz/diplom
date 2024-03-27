package ru.gb.springbootlesson3.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Reader;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ReaderRepository extends CrudRepository<Reader, Long> {

}
