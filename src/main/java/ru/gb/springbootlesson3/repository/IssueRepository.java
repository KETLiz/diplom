package ru.gb.springbootlesson3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.gb.springbootlesson3.entity.Issue;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue, Long> {

}
